package com.geektech.quizapp_gt_4_2.presentation.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.model.History;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<List<History>> history = new MutableLiveData<>();
    LiveData<List<History>> historyLiveData = App.historyStorage.getAllHistory();

}