package com.geektech.quizapp_gt_4_2.presentation.result;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.history.HistoryStorage;
import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.List;

public class ResultViewModel extends ViewModel {

      MutableLiveData<QuizResult> historyResult = new MutableLiveData<>();

            public  void getResult(int id){
              historyResult.setValue(App.quizDatabase.historyDao().get(id));
              }

}
