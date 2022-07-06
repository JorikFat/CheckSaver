package dev.kirillbalanov.check_sample.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.model.Config;
import dev.kirillbalanov.check_sample.model.CreateCheckDialog;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleViewModel extends ViewModel {
    public final MutableLiveData<List<Check>> checksData = new MutableLiveData<>();

    public void myCustomDialog(Context context, ChecksAdapter checksAdapter, Config config) {
        new CreateCheckDialog(context, new CreateCheckDialog.Callback() {
            @Override
            public void created(Check check) {
                checksAdapter.addChecks(checksData.getValue());
                config.saveCheck(check);
            }
        }).show();
    }

}
