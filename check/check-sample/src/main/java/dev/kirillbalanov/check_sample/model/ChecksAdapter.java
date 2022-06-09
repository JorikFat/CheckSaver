package dev.kirillbalanov.check_sample.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.pojo.Check;

public class ChecksAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private ArrayList<Check> checks;
    private int numberItems;

    public ChecksAdapter(ArrayList<Check> checks){
        this.checks = checks;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.check_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Check check = checks.get(position);
        holder.checkValue.setText(check.getAllValues());
    }

    @Override
    public int getItemCount() {
        return checks.size();
    }

}
