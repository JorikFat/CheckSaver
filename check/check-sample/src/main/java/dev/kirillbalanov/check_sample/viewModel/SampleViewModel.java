package dev.kirillbalanov.check_sample.viewModel;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import dev.kirillbalanov.check_sample.App;
import dev.kirillbalanov.check_sample.db.AppDateBase;
import dev.kirillbalanov.check_sample.model.ChecksAdapter;
import dev.kirillbalanov.check_sample.model.CreateCheckDialog;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleViewModel extends AndroidViewModel {

    private List<Check> checks = new ArrayList<>();

    private static AppDateBase db;

    private LiveData<List<Check>> checksData;
    public LiveData<List<Check>> getChecksData() {
        return checksData;
    }

    public SampleViewModel(@NonNull Application application) {
        super(application);
        db = App.getAppDateBase();
        checksData = db.checksDao().getAllChecks();
    }

    public void insertChecks(Check check){
        new Thread(() -> {
            db.checksDao().insertCheck(check);
            checks.add(check);
        }).start();
    }

    public void myCustomDialog(Context context, ChecksAdapter checksAdapter) {
        new CreateCheckDialog(context, check -> {
            insertChecks(check);
            checksAdapter.addChecks(checks);
        }).show();
    }
}