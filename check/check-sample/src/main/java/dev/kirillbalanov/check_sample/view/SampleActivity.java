package dev.kirillbalanov.check_sample.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import javax.inject.Inject;
import dev.kirillbalanov.check_sample.App;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.model.RecycleChecksAdapter;
import dev.kirillbalanov.check_sample.model.CreateCheckDialog;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {

    private RecyclerView checkRecycleView;
    private RecycleChecksAdapter checksRecycleAdapter;
    @Inject
    public SampleViewModel viewModel;

    private View addCheckBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        App.initSampleActivityComponent(this).injectSampleActivity(this);

        addCheckBtn = findViewById(R.id.btn_add);
        addCheckBtn.setOnClickListener(view -> myCustomDialog());

        checksRecycleAdapter = new RecycleChecksAdapter();

        viewModel.getChecksData().observe(this, checks -> {
            if (checks.isEmpty()) myCustomDialog();
            checksRecycleAdapter.setChecks(checks);
        });

        checkRecycleView = findViewById(R.id.rc_check);
        checkRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами
        checkRecycleView.setHasFixedSize(true);
        checkRecycleView.setAdapter(checksRecycleAdapter);
    }

    public void myCustomDialog() {
        new CreateCheckDialog(this, check -> viewModel.insertChecks(check)).show();
    }
}