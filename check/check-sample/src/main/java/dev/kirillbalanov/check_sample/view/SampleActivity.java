package dev.kirillbalanov.check_sample.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import dev.kirillbalanov.check_sample.R;
import dev.kirillbalanov.check_sample.di.components.DaggerViewComponent;
import dev.kirillbalanov.check_sample.di.components.ViewComponent;
import dev.kirillbalanov.check_sample.di.module.SampleModel;
import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.model.CreateCheckDialog;
import dev.kirillbalanov.check_sample.viewModel.SampleViewModel;

public class SampleActivity extends AppCompatActivity {

    private RecyclerView checkRecycleList;
    private ChecksAdapter checksAdapter;
    private SampleViewModel viewModel;
    private ViewComponent viewComponent;

    View addCheckBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        viewComponent = DaggerViewComponent.builder().sampleModel(new SampleModel(this)).build();
        viewModel = viewComponent.getViewModel();

        addCheckBtn = findViewById(R.id.btn_add);
        addCheckBtn.setOnClickListener(view -> myCustomDialog());

        checksAdapter = new ChecksAdapter();

        viewModel.getChecksData().observe(this, checks -> {
            if (checks.isEmpty()) myCustomDialog();
            checksAdapter.setChecks(checks);
        });

        checkRecycleList = findViewById(R.id.rc_check);
        checkRecycleList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //разделение между итемами
        checkRecycleList.setHasFixedSize(true);
        checkRecycleList.setAdapter(checksAdapter);
    }

    public void myCustomDialog() {
        new CreateCheckDialog(this, check -> viewModel.insertChecks(check)).show();
    }
}