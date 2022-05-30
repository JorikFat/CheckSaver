package dev.kirillbalanov.check_sample;

import static dev.kirillbalanov.check_sample.Const.dateFormat;
import static dev.kirillbalanov.check_sample.Const.timeFormat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;

import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private final String saveCheckKey = "save_check_key";
    private final String saveIdKey = "save_id_key";
    private final String saveTotalKey = "save_total_key";
    private final String saveDateKey = "save_date_key";
    private final String saveTimeKey = "save_time_key";

    private TextView checkValue;
    private Calendar calendar;

    private Check check;

    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        pref = getSharedPreferences("Storage", MODE_PRIVATE);

        checkValue = findViewById(R.id.tv_check);
        checkValue.setText(pref.getString(saveCheckKey, "enter the data"/*todo take from resources*/));

        check = new Check(
                pref.getLong(saveIdKey, 0),
                pref.getString(saveTotalKey, null),
                pref.getString(saveDateKey, null),
                pref.getString(saveTimeKey, null)
        );

        if (check.getTotal() == null && check.getDate() == null && check.getTime() == null) {
            myCustomDialog();
        }
    }

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

            date.setText(dateFormat.format(calendar.getTime()));
        };
        timeSetListener = (timePicker, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            time.setText(timeFormat.format(calendar.getTime()));
        };

        builder.setPositiveButton("OK"/*todo take from resources*/, (d, i) -> {
            check = new Check(1L, number.getText().toString(), date.getText().toString(), time.getText().toString());
            checkValue.setText(check.getAllValues());
            pref.edit()
                    .putString(saveCheckKey, check.getAllValues())
                    .putLong(saveIdKey, check.getId())
                    .putString(saveTotalKey, check.getTotal())
                    .putString(saveDateKey, check.getDate())
                    .putString(saveTimeKey, check.getTime())
                    .apply();
        });
        builder.setNegativeButton("Cancel"/*todo take from resources*/, (d, i) -> {
            //only close
        });
        builder.show();

        time.setOnClickListener(view -> showTimeDialog());
        date.setOnClickListener(view -> showDateDialog());
    }

    private void showTimeDialog() {
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(this, timeSetListener, hour, minute, false).show();
    }

    private void showDateDialog(){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, dateSetListener, year, month, day).show();
    }
}