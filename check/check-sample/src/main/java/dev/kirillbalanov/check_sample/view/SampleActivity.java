package dev.kirillbalanov.check_sample.view;

import static dev.kirillbalanov.check_sample.App.getAppComponent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.di.module.SampleActivityModule;
import dev.kirillbalanov.check_sample.model.RecycleChecksAdapter;
import dev.kirillbalanov.check_sample.model.CreateCheckDialog;
import dev.kirillbalanov.check_sample.pojo.Check;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {
    @Inject
    public SampleViewModel viewModel;
    RecycleChecksAdapter checksRecycleAdapter;
    Check choseCheck;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        getAppComponent().createActivityComponent(new SampleActivityModule(this)).injectSampleActivity(this);

        findViewById(R.id.btn_add).setOnClickListener(view -> myCustomDialog(null));

        checksRecycleAdapter = new RecycleChecksAdapter((choseCheck, isDeleted) -> {
            this.choseCheck = choseCheck;
            if(isDeleted) deleteItemInRecycle(choseCheck);
            else myCustomDialog(choseCheck.getCalendar());
        });

        viewModel.getChecksData().observe(this, checks -> {
            if (checks.isEmpty()) myCustomDialog(null);
            checksRecycleAdapter.setChecks(checks);
        });

        RecyclerView checkRecycleView = findViewById(R.id.rc_check);
        checkRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами
        checkRecycleView.setHasFixedSize(true);
        checkRecycleView.setAdapter(checksRecycleAdapter);
    }

    public void myCustomDialog(Calendar calendar) {
        if(calendar!=null) {
            new CreateCheckDialog(this, calendar, choseCheck, check -> viewModel.updateCheck(check)).show();
        } else new CreateCheckDialog(this, check -> viewModel.insertChecks(check)).show();
    }

    public void deleteItemInRecycle(Check deletedCheck){
        viewModel.deleteCheck(deletedCheck);
    }
}