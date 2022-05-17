package dev.kirillbalanov.check_sample;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SampleActivity extends AppCompatActivity {

    EditText number, date, time;
    Calendar calendar;

    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        number = findViewById(R.id.number_dialog);
        date = findViewById(R.id.date_dialog);
        time = findViewById(R.id.time_dialog);

        number.setInputType(InputType.TYPE_NULL);
        date.setInputType(InputType.TYPE_NULL); // создают тип инпутов необходимых для загрузки диалога, вместо текста
        time.setInputType(InputType.TYPE_NULL);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberDialog(number);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(time);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(date);
            }
        });
    }
    private void showNumberDialog(EditText number) {
    }

    private void showTimeDialog(EditText time) {
        calendar = Calendar.getInstance();
        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(SampleActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(EditText date){
        calendar = Calendar.getInstance();
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
                date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(SampleActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}