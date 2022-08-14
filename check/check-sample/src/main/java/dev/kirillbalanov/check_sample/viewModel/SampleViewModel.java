package dev.kirillbalanov.check_sample.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import dev.kirillbalanov.check_sample.db.AppDateBase;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleViewModel extends ViewModel {

    AppDateBase appDateBase;

    public SampleViewModel(AppDateBase appDateBase) {
        this.appDateBase = appDateBase;
    }

    public LiveData<List<Check>> getChecksData() {
        return appDateBase.checksDao().getAllChecks();
    }

    public void insertChecks(Check check){
        new Thread(() -> appDateBase.checksDao().insertCheck(check)).start();
    }
}