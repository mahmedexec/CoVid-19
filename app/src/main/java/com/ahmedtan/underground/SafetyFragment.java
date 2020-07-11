package com.ahmedtan.underground;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class SafetyFragment extends Fragment {
    private RecyclerView recyclerView;
    private SafetyAdapter safetyAdapter;
    private ArrayList<Safety> safetyArrayList;
    private ProgressBar progressBar;



    public SafetyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_safety, container, false);


        recyclerView = view.findViewById(R.id.safety_recycler);
        safetyArrayList = new ArrayList<>();
        progressBar = view.findViewById(R.id.pbSafety);


        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        safetyArrayList.add(new Safety(R.string.washing_hands, R.drawable.ic_washing_hands));
        safetyArrayList.add(new Safety(R.string.social_distancing, R.drawable.ic_social_distancing));
        safetyArrayList.add(new Safety(R.string.covering, R.drawable.ic_coughing));
        safetyArrayList.add(new Safety(R.string.avoid_smoking, R.drawable.no_smoking));
        safetyArrayList.add(new Safety(R.string.stay_home, R.drawable.ic_stay_home));
        safetyArrayList.add(new Safety(R.string.santize, R.drawable.ic_sanitizer));


        safetyAdapter = new SafetyAdapter(safetyArrayList, getActivity());
        recyclerView.setAdapter(safetyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        return view;
    }
}