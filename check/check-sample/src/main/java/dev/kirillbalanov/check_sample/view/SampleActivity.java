package dev.kirillbalanov.check_sample.view;

import static dev.kirillbalanov.check_sample.App.getAppComponent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import javax.inject.Inject;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.di.module.SampleActivityModule;
import dev.kirillbalanov.check_sample.model.RecycleChecksAdapter;
import dev.kirillbalanov.check_sample.model.CreateCheckDialog;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {
    @Inject
    public SampleViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        getAppComponent().createActivityComponent(new SampleActivityModule(this)).injectSampleActivity(this);

        findViewById(R.id.btn_add).setOnClickListener(view -> myCustomDialog());

        RecycleChecksAdapter checksRecycleAdapter = new RecycleChecksAdapter();

        viewModel.getChecksData().observe(this, checks -> {
            if (checks.isEmpty()) myCustomDialog();
            checksRecycleAdapter.setChecks(this, checks);
        });

        RecyclerView checkRecycleView = findViewById(R.id.rc_check);
        checkRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами
        checkRecycleView.setHasFixedSize(true);
        checkRecycleView.setAdapter(checksRecycleAdapter);
    }

    public void myCustomDialog() {
        new CreateCheckDialog(this, check -> viewModel.insertChecks(check)).show();
    }
}