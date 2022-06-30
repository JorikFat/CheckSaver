package dev.kirillbalanov.check_sample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleActivity extends AppCompatActivity {

    private Config config;

    RecyclerView checkRecycleList;
    ChecksAdapter checksAdapter;
    private ViewModel viewModel;

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
        new CreateCheckDialog(this, check -> {
            checksAdapter.addChecks(check);
            config.saveCheck(check);
        }).show();
    }

    private void connectViewModel() {
        viewModel = new ViewModelProvider(this).get(SampleViewModel.class);
        Log.i("LifeCycle", "Refresh");
    }
}