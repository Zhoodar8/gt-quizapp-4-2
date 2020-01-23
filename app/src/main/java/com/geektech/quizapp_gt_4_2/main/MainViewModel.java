package com.geektech.quizapp_gt_4_2.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<Integer> countList = new MutableLiveData<>();
    private Integer count = 0;

    public MainViewModel() {
        Log.d("ololo", "View model create");
        message.setValue("Hello Observer");
    }
    public void onLoginClick() {

    }
    public void onIncrementClick() {
            countList.setValue(count++);
    }

    public void decrement() {
        countList.setValue(count--);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
