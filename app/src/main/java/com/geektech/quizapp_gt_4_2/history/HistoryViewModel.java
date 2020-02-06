package com.geektech.quizapp_gt_4_2.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    LiveData<List<QuizResult>> list = new LiveData<List<QuizResult>>() {};
    LiveData<List<QuizResult>> history = list;

}
