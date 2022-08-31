package dev.kirillbalanov.check_sample.model;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.kirillbalanov.check_sample.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView checkValue;
    public View editCheckBtn;
    public View deleteCheckBtn;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        checkValue = itemView.findViewById(R.id.tv_check);

        editCheckBtn = itemView.findViewById(R.id.editButton);
        deleteCheckBtn = itemView.findViewById(R.id.deleteButton);
    }
}