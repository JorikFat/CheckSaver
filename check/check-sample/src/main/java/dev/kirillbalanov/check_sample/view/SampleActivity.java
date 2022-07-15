package dev.kirillbalanov.check_sample.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {

    RecyclerView checkRecycleList;
    ChecksAdapter checksAdapter;
    private SampleViewModel viewModel;

    Button addCheckBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        viewModel = new ViewModelProvider(this).get(SampleViewModel.class);

        addCheckBtn = findViewById(R.id.btn_add);
        addCheckBtn.setOnClickListener(view -> viewModel.myCustomDialog(SampleActivity.this, checksAdapter));


        checksAdapter = new ChecksAdapter();

        viewModel.getChecksData().observe(this, checks -> {
            if (checks.isEmpty()) viewModel.myCustomDialog(SampleActivity.this, checksAdapter);
            checksAdapter.addChecks(checks);
        });

        checkRecycleList = findViewById(R.id.rc_check);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        checkRecycleList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами

        checkRecycleList.setLayoutManager(linearLayout);
        checkRecycleList.setHasFixedSize(true);
        checkRecycleList.setAdapter(checksAdapter);
    }
}