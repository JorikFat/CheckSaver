package dev.kirillbalanov.check_sample.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.kirillbalanov.check_sample.App;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.di.module.AppModule;
import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.model.CreateCheckDialog;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {

    RecyclerView checkRecycleList;
    ChecksAdapter checksAdapter;
    private SampleViewModel viewModel;

    View addCheckBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        viewModel = new AppModule(this).getViewModel();

        addCheckBtn = findViewById(R.id.btn_add);
        addCheckBtn.setOnClickListener(view -> myCustomDialog());

        checksAdapter = new ChecksAdapter();

        viewModel.getChecksData().observe(this, checks -> {
            if (checks.isEmpty()) myCustomDialog();
            checksAdapter.setChecks(checks);
        });

        checkRecycleList = findViewById(R.id.rc_check);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        checkRecycleList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами

        checkRecycleList.setLayoutManager(linearLayout);
        checkRecycleList.setHasFixedSize(false);
        checkRecycleList.setAdapter(checksAdapter);

        App.getAppComponent().inject(this);
    }

    public void myCustomDialog() {
        new CreateCheckDialog(this, check -> viewModel.insertChecks(check)).show();
    }
}