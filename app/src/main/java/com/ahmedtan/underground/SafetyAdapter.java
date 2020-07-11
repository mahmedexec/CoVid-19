package com.ahmedtan.underground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SafetyAdapter extends RecyclerView.Adapter<SafetyAdapter.SafetyViewHolder> {
    private ArrayList<Safety> safetyArrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public SafetyAdapter(ArrayList<Safety> safetyArrayList, Context context) {
        this.safetyArrayList = safetyArrayList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SafetyAdapter.SafetyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.safety_item, parent, false);
        return new SafetyViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SafetyAdapter.SafetyViewHolder holder, int position) {
        holder.setData(safetyArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return safetyArrayList.size();
    }

    public class SafetyViewHolder extends RecyclerView.ViewHolder {
        private TextView adviceText;
        private ImageView imageView;


        public SafetyViewHolder(@NonNull View itemView, SafetyAdapter safetyAdapter) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_action);
            adviceText = itemView.findViewById(R.id.advice_text);
        }

        public void setData(Safety safety) {
            adviceText
                    .setText(
                            context.getResources()
                                    .getText(
                                            safety.getResString()
                                    )
                    );
            Glide.with(context).load(safety.getResImage()).into(imageView);
        }
    }
}
