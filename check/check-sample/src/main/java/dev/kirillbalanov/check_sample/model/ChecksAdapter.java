package dev.kirillbalanov.check_sample.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.pojo.Check;

public class ChecksAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private final List<Check> checks = new ArrayList<>();
    Check currentCheck;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.check_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        currentCheck = checks.get(position);
        holder.checkValue.setText(currentCheck.getAllValues());
    }

    @Override
    public int getItemCount() {
        return checks.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addChecks(Check check){
        if(check.getTotal() != null && check.getDate() != null && check.getTime() != null) {
            currentCheck = check;
            checks.add(currentCheck);
            notifyDataSetChanged();
        }
    }
}