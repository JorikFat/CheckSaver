package dev.kirillbalanov.check_sample.model;

import android.content.SharedPreferences;

import dev.kirillbalanov.check_sample.pojo.Check;

public class Config {
    private final SharedPreferences sharedPreferences;
    private final String saveCheckKey = "save_check_key";
    private final String saveIdKey = "save_id_key";
    private final String saveTotalKey = "save_total_key";
    private final String saveDateKey = "save_date_key";
    private final String saveTimeKey = "save_time_key";

    public Config(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveCheck(Check check){
        sharedPreferences.edit()
                .putString(saveCheckKey, check.getAllValues())
                .putLong(saveIdKey, check.getId())
                .putString(saveTotalKey, check.getTotal())
                .putString(saveDateKey, check.getDate())
                .putString(saveTimeKey, check.getTime())
                .apply();
    }

    public Check readCheck(){
        if(sharedPreferences.getLong(saveIdKey, 0) == 0) return null;
        return new Check(
                sharedPreferences.getLong(saveIdKey, 0),
                sharedPreferences.getString(saveTotalKey, null),
                sharedPreferences.getString(saveDateKey, null),
                sharedPreferences.getString(saveTimeKey, null)
        );
    }
}
