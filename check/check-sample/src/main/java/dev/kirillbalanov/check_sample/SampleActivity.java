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

import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleActivity extends AppCompatActivity {
    //objects for my saved preferences
    private SharedPreferences pref;
    private final String saveCheckKey = "save_check_key";
    private final String saveIdKey = "save_id_key";
    private final String saveTotalKey = "save_total_key";
    private final String saveDateKey = "save_date_key";
    private final String saveTimeKey = "save_time_key";

    TextView checkValue;
    Calendar calendar;

    private Check check;

    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        //initialize preferences and set start latest saved date for check
        pref = getSharedPreferences("Storage", MODE_PRIVATE);


//        checkValue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showChangeCheckDialog();
//            }
//        });
        checkValue = findViewById(R.id.tv_check);
        checkValue.setText(pref.getString(saveCheckKey, "enter the data"));

        check = new Check(pref.getLong(saveIdKey, 0), pref.getString(saveTotalKey, null), pref.getString(saveDateKey, null), pref.getString(saveTimeKey, null));

        if (check.getTotal() == null && check.getDate() == null && check.getTime() == null) {
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

        dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(simpleDateFormat.format(calendar.getTime()));
        };
        timeSetListener = (timePicker, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

            time.setText(simpleDateFormat.format(calendar.getTime()));
        };

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            check = new Check(1L, number.getText().toString(), date.getText().toString(), time.getText().toString());
            checkValue.setText(check.getAllValues());
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(saveCheckKey, check.getAllValues());
            edit.putLong(saveIdKey, check.getId());
            edit.putString(saveTotalKey, check.getTotal());
            edit.putString(saveDateKey, check.getDate());
            edit.putString(saveTimeKey, check.getTime());
            edit.apply();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            //only close
        });
        builder.show();

        time.setOnClickListener(view -> showTimeDialog());
        date.setOnClickListener(view -> showDateDialog());
    }

    private void showTimeDialog() {
        calendar = Calendar.getInstance();
        new TimePickerDialog(SampleActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(){
        calendar = Calendar.getInstance();
        new DatePickerDialog(SampleActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

//    private  void showChangeCheckDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        ConstraintLayout customLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.change_check, null);
//
//        builder.setView(customLayout);
//
//        builder.setPositiveButton("OK", (dialogInterface, i) -> {
//
//                });
//
//        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
//                    //only close
//                });
//        builder.show();
//    }
}