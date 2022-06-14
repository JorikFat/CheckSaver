package dev.kirillbalanov.check_sample.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.kirillbalanov.check_sample.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView checkValue;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        checkValue = itemView.findViewById(R.id.tv_check);
        checkValue.setText(R.string.empty_text);//todo не надо отображать "текст заглушку". Если чеков нет - будет пустой экран
    }
}