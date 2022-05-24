package dev.kirillbalanov.check_sample;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SampleActivity extends AppCompatActivity {
    //objects for my saved preferences
    private SharedPreferences pref;
    private final String saveKey = "save key";

    TextView check;
    Calendar calendar;

    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        check = findViewById(R.id.tv_check);
        //initialize preferences and set start latest saved date for check
        pref = getSharedPreferences("Storage", MODE_PRIVATE);
        check.setText(pref.getString(saveKey, "enter the data"));

        if(check.getText().toString().equals("enter the data")) {
            myCustomDialog();
        }
    }

    @SuppressLint("SetTextI18n")
    private void myCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ConstraintLayout customLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.layout_custom, null);

        TextView time = customLayout.findViewById(R.id.time_dialog);
        TextView date = customLayout.findViewById(R.id.date_dialog);
        EditText number = customLayout.findViewById(R.id.number_dialog);

        builder.setView(customLayout);

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            check.setText("Summary: " + number.getText() + " Date: " + date.getText() + " Time: " + time.getText());
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(saveKey, check.getText().toString());
            edit.apply();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            //only close
        });
        builder.show();

        time.setOnClickListener(view -> showTimeDialog(time));
        date.setOnClickListener(view -> showDateDialog(date));
    }

    private void showTimeDialog(TextView time) {
        calendar = Calendar.getInstance();
        timeSetListener = (timePicker, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

            time.setText(simpleDateFormat.format(calendar.getTime()));
        };
        new TimePickerDialog(SampleActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(TextView date){
        calendar = Calendar.getInstance();
        dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(simpleDateFormat.format(calendar.getTime()));
        };
        new DatePickerDialog(SampleActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}