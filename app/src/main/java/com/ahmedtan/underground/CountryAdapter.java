package com.ahmedtan.underground;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private ArrayList<Country> countryList;
    private LayoutInflater layoutInflater;
    private String plus = "+ ";
    Context context;

    public CountryAdapter(Context context, ArrayList<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
        layoutInflater = LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.country_item_layout, parent, false);
        return new CountryViewHolder(view, this);
    }


    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.setData(countryList.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        private TextView newCasesText;
        private TextView newRecoveredText;
        private TextView newDeathsText;
        private CountryAdapter countryAdapter;
        private TextView countryName;
        private TextView countryPosition;
        private Country countryObj;

        private ImageView countryFlag;


        public CountryViewHolder(@NonNull View itemView, CountryAdapter countryAdapter) {
            super(itemView);
            this.countryAdapter = countryAdapter;
            newCasesText = itemView.findViewById(R.id.country_new_cases);
            newRecoveredText = itemView.findViewById(R.id.country_new_recovered);
            newDeathsText = itemView.findViewById(R.id.country_new_deaths);
            countryName = itemView.findViewById(R.id.country_name_text);
            countryFlag = itemView.findViewById(R.id.country_flag);
            countryPosition = itemView.findViewById(R.id.country_position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CountryDetailsActivity.class);
                    intent.putExtra("countryObj",countryObj);
                    context.startActivity(intent);
                }
            });


        }


        @SuppressLint("DefaultLocale")
        void setData(Country country, int position) {
            countryPosition.setText(String.valueOf(position));
            countryName.setText(country.getCountry());
            newCasesText.setText(String.format("%s%s", plus, country.getNewConfirmed()));
            newRecoveredText.setText(String.format("%s%s", plus, country.getNewRecovered()));
            newDeathsText.setText(String.format("%s%s", plus, country.getNewDeaths()));
            Glide.with(context).load(country.getFlagUrl()).into(countryFlag);
            countryObj = country;


        }
    }
}
