package dev.kirillbalanov.check_sample.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import dev.kirillbalanov.check_sample.db.AppDataBase;
import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleViewModel extends ViewModel {

    private static AppDataBase appDataBase;

    public SampleViewModel(AppDataBase appDateBase) {
        appDataBase = appDateBase;
    }

    public LiveData<List<Check>> getChecksData() {
        return appDataBase.checksDao().getAllChecks();
    }

    public void insertChecks(Check check){
        new Thread(() -> appDataBase.checksDao().insertCheck(check)).start();
    }

    public static void deleteCheck(Check check){
        new Thread(() -> appDataBase.checksDao().delete(check)).start();
    }
}