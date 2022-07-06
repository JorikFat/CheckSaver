package dev.kirillbalanov.check_sample.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.model.Config;
import dev.kirillbalanov.check_sample.pojo.Check;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {

    private Config config;

    RecyclerView checkRecycleList;
    ChecksAdapter checksAdapter;
    private SampleViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        config = new Config(getSharedPreferences("Storage", MODE_PRIVATE));

        Check check = config.readCheck();

        checksAdapter = new ChecksAdapter();
        if(check !=null && check.isValid() && viewModel!=null) {
//        checksAdapter.addChecks(check);
//        else myCustomDialog();
            viewModel.checksData.observe(this, this::showChecks);
        }

        setupViewModel();

        checkRecycleList = findViewById(R.id.rc_check);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        checkRecycleList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами

        checkRecycleList.setLayoutManager(linearLayout);
        checkRecycleList.setHasFixedSize(true);
        checkRecycleList.setAdapter(checksAdapter);
    }

//    private void myCustomDialog() {
//        new CreateCheckDialog(this, check -> {
//            checksAdapter.addChecks(check);
//            config.saveCheck(check);
//        }).show();
//    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(SampleViewModel.class);
        viewModel.checksData.observe(this, this::showChecks);
        viewModel.myCustomDialog(this, checksAdapter, config);
    }

    private void showChecks (List<Check> checks) {
        if (checks.isEmpty()) {
            Toast.makeText(this, "Check is empty", Toast.LENGTH_SHORT).show();
        } else {
            checksAdapter.addChecks(checks);
        }
    }
}