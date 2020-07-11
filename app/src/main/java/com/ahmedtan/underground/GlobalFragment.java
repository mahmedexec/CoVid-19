package com.ahmedtan.underground;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class GlobalFragment extends Fragment {

    private final String LOG_TAG = GlobalFragment.class.getSimpleName();
    private final String SUMMARY_URL = "https://corona.lmao.ninja/v2/all?yesterday";
    private TextView newCasesText;
    private TextView newRecoveredText;
    private TextView newDeathsText;
    private TextView totalCasesText;
    private TextView totalRecoveriesText;
    private TextView totalDeathsText;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private ImageButton imageButton;
    private ImageView logo0;

    public GlobalFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate");
        getData();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_global, container, false);

        logo0 = view.findViewById(R.id.logo_0);

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                Glide.with(getActivity()).load(R.drawable.logo_white).into(logo0);

                break;
            case Configuration.UI_MODE_NIGHT_NO:
                Glide.with(getActivity()).load(R.drawable.logo_blue).into(logo0);

                break;
        }


        newCasesText = view.findViewById(R.id.new_cases_text);
        newRecoveredText = view.findViewById(R.id.new_recovered_text);
        newDeathsText = view.findViewById(R.id.new_deaths_text);
        totalCasesText = view.findViewById(R.id.total_cases_text);
        totalRecoveriesText = view.findViewById(R.id.total_recovered_text);
        totalDeathsText = view.findViewById(R.id.total_deaths_text);
        relativeLayout = view.findViewById(R.id.global_relative_layout);
        progressBar = view.findViewById(R.id.progress_circular);

        imageButton = view.findViewById(R.id.refresh_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                getData();
                ByCountryFragment.getInstance().getData();


            }
        });

        return view;
    }

    public void getData() {
        if (isNetworkAvailable()) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, SUMMARY_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d(LOG_TAG, response.toString());
                    processData(response.toString());
                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    new AlertDialog.Builder(requireActivity())
                            .setMessage("Error retrieving data from the Server")
                            .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    getData();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressBar.setVisibility(View.GONE);

                                }
                            })
                            .create().show();

                }
            });
            MySingleton.getInstance(requireActivity().getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        } else {
            new AlertDialog.Builder(requireActivity())
                    .setMessage(R.string.connection_string)
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                            ByCountryFragment.getInstance().getData();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressBar.setVisibility(View.GONE);

                        }
                    })
                    .create().show();

        }

    }


    private void processData(String jsonResponse) {
        try {

            JSONObject globalObject = new JSONObject(jsonResponse);

            newCasesText.setText(globalObject.get("todayCases").toString());
            newRecoveredText.setText(globalObject.get("todayRecovered").toString());
            newDeathsText.setText(globalObject.get("todayDeaths").toString());
            totalCasesText.setText(globalObject.get("cases").toString());
            totalRecoveriesText.setText(globalObject.get("recovered").toString());
            totalDeathsText.setText(globalObject.get("deaths").toString());
            progressBar.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}