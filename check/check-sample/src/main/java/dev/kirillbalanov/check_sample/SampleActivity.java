package dev.kirillbalanov.check_sample;

import static dev.jorik.checksaver.core.Utils.dateFormat;
import static dev.jorik.checksaver.core.Utils.timeFormat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleActivity extends AppCompatActivity {

    private final ArrayList<Check> checks = new ArrayList<>();//todo не надо хранить список чеков в Acitivity, они должны быть только в RecyclerView.Adapter

    private SharedPreferences pref;
    private final String saveCheckKey = "save_check_key";
    private final String saveIdKey = "save_id_key";
    private final String saveTotalKey = "save_total_key";
    private final String saveDateKey = "save_date_key";
    private final String saveTimeKey = "save_time_key";

    private Calendar calendar;

    private Check check;

    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    TextView date;
    TextView time;

    RecyclerView checkRecycleList;
    ChecksAdapter checksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        pref = getSharedPreferences("Storage", MODE_PRIVATE);

        check = new Check(
                pref.getLong(saveIdKey, 0),
                pref.getString(saveTotalKey, null),
                pref.getString(saveDateKey, null),
                pref.getString(saveTimeKey, null)
        );

        checks.add(check);
        checkRecycleList = findViewById(R.id.rc_check);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        checkRecycleList.setLayoutManager(linearLayout);
        checkRecycleList.setHasFixedSize(true);
        checksAdapter = new ChecksAdapter(checks);
        checkRecycleList.setAdapter(checksAdapter);

        if (check.getTotal() == null && check.getDate() == null && check.getTime() == null) {
            myCustomDialog();
        }
    }

    private void myCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ConstraintLayout customLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.layout_custom, null);

        EditText number = customLayout.findViewById(R.id.number_dialog);
        date = customLayout.findViewById(R.id.date_dialog);
        time = customLayout.findViewById(R.id.time_dialog);

        builder.setView(customLayout);

        dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
        };
        timeSetListener = (timePicker, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
        };

        builder.setPositiveButton(getString(R.string.positive_variant), (d, i) -> {
            check = new Check(1L, number.getText().toString(), date.getText().toString(), time.getText().toString());
            checks.add(check);
            pref.edit()
                    .putString(saveCheckKey, check.getAllValues())
                    .putLong(saveIdKey, check.getId())
                    .putString(saveTotalKey, check.getTotal())
                    .putString(saveDateKey, check.getDate())
                    .putString(saveTimeKey, check.getTime())
                    .apply();
            /*
            todo не надо вызывать recrete() | recreate() вызывается только, когда изменилась конфигурация
            todo нужно класть данные в Preferences, и отображать их
            */
            recreate(); //reload activity for start OnCreate again
        });
        builder.setNegativeButton(getString(R.string.negative_variant), (d, i) -> {/*only close*/});
        builder.setTitle(getString(R.string.new_dialog_title));
        builder.show();


        date.setOnClickListener(view -> showDateDialog());
        time.setOnClickListener(view -> showTimeDialog());
    }

    private void showTimeDialog() {
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(this, timeSetListener, hour, minute, true).show();
        time.setText(timeFormat.format(calendar.getTime()));//todo отображается текущая дата, даже если закрыть timePicker
    }

    @SuppressLint("NewApi")//todo не нужно ограничивать работу на разных устройствах
    private void showDateDialog(){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.positive_variant), (dialogInterface, i) -> {//todo второй раз переопределяется кнопка выбора даты
            datePickerDialog.setOnDateSetListener(dateSetListener);
            date.setText(dateFormat.format(calendar.getTime()));
            showTimeDialog();//todo вызывать открытие timePicker'а в самом datePickerListener
        });
    }
}