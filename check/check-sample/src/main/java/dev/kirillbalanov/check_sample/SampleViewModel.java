package dev.kirillbalanov.check_sample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.SortedList;

import java.util.List;

import dev.kirillbalanov.check_sample.pojo.Check;

public class SampleViewModel extends ViewModel {
    MutableLiveData<Check> data;

}
