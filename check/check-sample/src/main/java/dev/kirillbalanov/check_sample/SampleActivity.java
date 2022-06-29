package dev.kirillbalanov.check_sample;

import static dev.jorik.checksaver.core.Utils.dateFormat;
import static dev.jorik.checksaver.core.Utils.timeFormat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;
import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleActivity extends AppCompatActivity {

    private Config config;

    private final Calendar calendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    EditText number;
    TextView date;
    TextView time;

    RecyclerView checkRecycleList;
    ChecksAdapter checksAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        config = new Config(getSharedPreferences("Storage", MODE_PRIVATE));

        Check check = config.readCheck();

        checksAdapter = new ChecksAdapter();
        if(check !=null && check.isValid())
        checksAdapter.addChecks(check);
        else myCustomDialog();

        checkRecycleList = findViewById(R.id.rc_check);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        checkRecycleList.setLayoutManager(linearLayout);
        checkRecycleList.setHasFixedSize(true);
        checkRecycleList.setAdapter(checksAdapter);
    }

    private void myCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ConstraintLayout customLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.layout_custom, null);

        number = customLayout.findViewById(R.id.number_dialog);
        date = customLayout.findViewById(R.id.date_dialog);
        time = customLayout.findViewById(R.id.time_dialog);

        builder.setView(customLayout);

        dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            date.setText(dateFormat.format(calendar.getTime()));
            showTimeDialog();
        };
        timeSetListener = (timePicker, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            time.setText(timeFormat.format(calendar.getTime()));
        };

        builder.setPositiveButton(getString(R.string.positive_variant), (d, i) -> {
            Check check = new Check(1, number.getText().toString(), date.getText().toString(), time.getText().toString());
            if(check.isValid()) {
                config.saveCheck(check);
                checksAdapter.addChecks(check);
            }
        });
        builder.setNegativeButton(getString(R.string.negative_variant), (d, i) -> {/*only close*/});
        builder.setTitle(getString(R.string.new_dialog_title));
        builder.show();

        date.setOnClickListener(view -> showDateDialog());
        time.setOnClickListener(view -> showTimeDialog());
    }

    private void showTimeDialog() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, true);
        timePickerDialog.show();
    }

    private void showDateDialog(){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }
}