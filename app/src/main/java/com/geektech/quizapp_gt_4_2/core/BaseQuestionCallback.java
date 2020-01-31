package com.geektech.quizapp_gt_4_2.core;

import androidx.recyclerview.widget.SortedList;

import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

import javax.security.auth.callback.Callback;

public interface BaseQuestionCallback<T> {

    void onSuccess(T t);

    void onFailure(Exception e);

}
