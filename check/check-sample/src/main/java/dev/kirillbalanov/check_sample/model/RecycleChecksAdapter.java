package dev.kirillbalanov.check_sample.model;

import static dev.kirillbalanov.check_sample.pojo.Check.longToCalendar;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.pojo.Check;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class RecycleChecksAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<Check> checks = new ArrayList<>();
    private Context context;

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
    public void setChecks(Context context, List<Check> checks){
        this.context = context;
        this.checks = checks;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteItem( int position){
        SampleViewModel.deleteCheck(checks.get(position));
        checks.remove(position);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void editItem(int position){
        Long time = checks.get(position).getDataCalendarInLong();
        Calendar calendar = longToCalendar(time);
        new CreateCheckDialog(context, calendar, checks.get(position), check -> {
            checks.remove(position);
            checks.add(position,check);
            notifyDataSetChanged();
        }).show();
    }
}