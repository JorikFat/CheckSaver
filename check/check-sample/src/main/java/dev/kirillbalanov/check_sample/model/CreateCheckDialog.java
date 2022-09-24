package dev.kirillbalanov.check_sample.model;

import static dev.jorik.checksaver.core.Utils.dateFormat;
import static dev.jorik.checksaver.core.Utils.timeFormat;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Calendar;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.pojo.Check;

public class CreateCheckDialog extends AlertDialog {
    private final Callback createCallback;
    private EditText number;
    private TextView date;
    private TextView time;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    private Calendar calendar = null;

    Check check = null;

    long idCheck;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        @SuppressLint("InflateParams") ConstraintLayout customLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.layout_custom, null);
        setView(customLayout);

        number = customLayout.findViewById(R.id.number_dialog);
        date = customLayout.findViewById(R.id.date_dialog);
        time = customLayout.findViewById(R.id.time_dialog);

        if(calendar!=null && check!=null) {
            number.setText(check.getTotal().toString());
            date.setText(dateFormat.format(calendar.getTime()));
            time.setText(timeFormat.format(calendar.getTime()));
        }

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

        date.setOnClickListener(view -> showDateDialog());
        time.setOnClickListener(view -> showTimeDialog());

        setButton(BUTTON_POSITIVE, getContext().getString(R.string.positive_variant), (d, i) -> {
                if(check!=null && check.isValid()){
                    check.setTotal(Float.valueOf(number.getText().toString()));
                    check.setCalendar(calendar);
                    createCallback.created(check);
                } else if(!number.getText().toString().isEmpty() & calendar!=null){
                    Check check = new Check(idCheck, Float.valueOf(number.getText().toString()), calendar);
                    if (check.isValid()) {
                        createCallback.created(check);
                        idCheck++;
                    }
                }
        });
        setButton(BUTTON_NEGATIVE, getContext().getString(R.string.negative_variant), (d, i) -> {/*only close*/});
        setTitle(getContext().getString(R.string.new_dialog_title));

        super.onCreate(savedInstanceState);
    }

    public CreateCheckDialog(Context context, Callback createCallback) {
        super(context);
        this.createCallback = createCallback;
    }

    public CreateCheckDialog(Context context, Calendar calendar, Check check, Callback createCallback) {
        super(context);
        this.createCallback = createCallback;
        this.calendar = calendar;
        this.check = check;
    }

    private void showDateDialog(){
        if(calendar==null) calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
        setButton(BUTTON_POSITIVE, getContext().getString(R.string.positive_variant), (d, i) -> {calendar = null;});
        datePickerDialog.show();
    }

    private void showTimeDialog() {
        if(calendar==null) calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), timeSetListener, hour, minute, true);
        setButton(BUTTON_POSITIVE, getContext().getString(R.string.positive_variant), (d, i) -> {calendar = null;});
        timePickerDialog.show();
    }

    public interface Callback {
        void created(Check check);
    }
}