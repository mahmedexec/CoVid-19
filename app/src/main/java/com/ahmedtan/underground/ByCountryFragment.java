package com.ahmedtan.underground;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ByCountryFragment extends Fragment {
    private RecyclerView recyclerView;
    private String LOG_TAG = ByCountryFragment.class.getSimpleName();
    private ArrayList<Country> countryList;
    private CountryAdapter countryAdapter;
    private ProgressBar progressBar;
    private Context context;
    private static ByCountryFragment instance=null;

    private final String COUNTRY_URL = "https://corona.lmao.ninja/v2/countries?yesterday&sort=cases";

    public ByCountryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;

        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bycountry, container, false);



        context= getActivity();
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.pBar);
        countryList = new ArrayList<>();



        return view;
    }

    public void getData() {

        Toast.makeText(requireActivity(),"Hey I'm up",Toast.LENGTH_SHORT).show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(COUNTRY_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                processData(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG, "Error retrieving country data");
            }
        });
        MySingleton.getInstance(requireActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest);

    }

    private void processData(String jsonResponse) {
        try {
            JSONArray countriesArr = new JSONArray(jsonResponse);
            for (int i = 0; i < countriesArr.length(); i++) {
                JSONObject countryObject = countriesArr.getJSONObject(i);
                JSONObject countryInfo = countryObject.getJSONObject("countryInfo");
                countryList.add(new Country(
                                countryObject.get("country").toString(),
                                countryInfo.get("iso2").toString(),
                                countryObject.get("todayCases").toString(),
                                countryObject.get("cases").toString(),
                                countryObject.get("todayDeaths").toString(),
                                countryObject.get("deaths").toString(),
                                countryObject.get("todayRecovered").toString(),
                                countryObject.get("recovered").toString(),
                                countryObject.get("active").toString(),
                                countryObject.get("critical").toString(),
                                countryObject.get("casesPerOneMillion").toString(),
                                countryInfo.get("flag").toString()


                        )

                );
            }
            Log.d(LOG_TAG, countryList.get(10).toString());
            countryAdapter = new CountryAdapter(context, countryList);
            recyclerView.setAdapter(countryAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ByCountryFragment getInstance() {
        return instance;
    }
}