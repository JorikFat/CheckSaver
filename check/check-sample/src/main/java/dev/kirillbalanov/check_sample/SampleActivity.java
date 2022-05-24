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

import dev.kirillbalanov.check_sample.pojo.Bill;

public class SampleActivity extends AppCompatActivity {
    //objects for my saved preferences
    private SharedPreferences pref;
    private final String saveBillKey = "save_bill_key";

    TextView billValue;
    Calendar calendar;

    private Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        //initialize preferences and set start latest saved date for check
        pref = getSharedPreferences("Storage", MODE_PRIVATE);

        billValue = findViewById(R.id.tv_check);
        billValue.setText(pref.getString(saveBillKey, "enter the data"));

        if(billValue.getText().toString().equals("enter the data")) {
            myCustomDialog();
        }
    }

    @SuppressLint("SetTextI18n")
    private void myCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ConstraintLayout customLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.layout_custom, null);

        EditText number = customLayout.findViewById(R.id.number_dialog);
        TextView date = customLayout.findViewById(R.id.date_dialog);
        TextView time = customLayout.findViewById(R.id.time_dialog);

        builder.setView(customLayout);

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            bill = new Bill(number.getText().toString(), date.getText().toString(), time.getText().toString());
            billValue.setText(bill.getAllValues());
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(saveBillKey, bill.getTotal());
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
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

            time.setText(simpleDateFormat.format(calendar.getTime()));
        };
        new TimePickerDialog(SampleActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(TextView date){
        calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(simpleDateFormat.format(calendar.getTime()));
        };
        new DatePickerDialog(SampleActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}