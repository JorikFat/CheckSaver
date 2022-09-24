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
import dev.kirillbalanov.check_sample.view.SampleActivity;

public class RecycleChecksAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<Check> checks = new ArrayList<>();
    AdapterCallback adapterCallback;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.check_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (checks.get(position).isValid()) holder.checkValue.setText(checks.get(position).toString());
        holder.deleteCheckBtn.setOnClickListener(view -> deleteItem(position));
        holder.editCheckBtn.setOnClickListener(view -> editItem(position));
    }

    @Override
    public int getItemCount() {
        return checks.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setChecks(List<Check> checks){
        this.checks = checks;
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        adapterCallback.created(checks.get(position), true);
    }

    public void editItem(int position){
        adapterCallback.created(checks.get(position), false);
    }

    public RecycleChecksAdapter(AdapterCallback adapterCallback){
        this.adapterCallback = adapterCallback;
    }

    public interface AdapterCallback {
        void created(Check choseCheck, Boolean isDeleted);
    }
}