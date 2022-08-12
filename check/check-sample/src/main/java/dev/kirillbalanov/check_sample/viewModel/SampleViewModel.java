package dev.kirillbalanov.check_sample.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import dev.kirillbalanov.check_sample.App;
import dev.kirillbalanov.check_sample.db.AppDateBase;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleViewModel extends ViewModel {

    private static AppDateBase db;

    private LiveData<List<Check>> checksData;
    public LiveData<List<Check>> getChecksData() {
        return checksData;
    }

    public SampleViewModel() {
        db = App.getAppComponent().getDateBase();
        checksData = db.checksDao().getAllChecks();
    }

    public void insertChecks(Check check){
        new Thread(() -> db.checksDao().insertCheck(check)).start();
    }
}