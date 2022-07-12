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
import dev.kirillbalanov.check_sample.pojo.Check;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {

    RecyclerView checkRecycleList;
    ChecksAdapter checksAdapter;
    private SampleViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        viewModel = new ViewModelProvider(this).get(SampleViewModel.class);

        boolean checkIsEmpty = viewModel.getCheck();

        checksAdapter = new ChecksAdapter();
        while(viewModel!=null) {
            if (checkIsEmpty) viewModel.myCustomDialog(this, checksAdapter);
            else {
                viewModel.getChecksData().observe(this, checks -> checksAdapter.addChecks(checks));
            }
        }

        checkRecycleList = findViewById(R.id.rc_check);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        checkRecycleList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами

        checkRecycleList.setLayoutManager(linearLayout);
        checkRecycleList.setHasFixedSize(true);
        checkRecycleList.setAdapter(checksAdapter);
    }
}