package com.geektech.quizapp_gt_4_2.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.geektech.quizapp_gt_4_2.core.BaseQuestionCallback;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.model.Global;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public interface IQuizApiClient {

    MutableLiveData<Question> getQuestions(int amount, Integer category, String difficulty, QuiestionCallback callback);
    MutableLiveData<Category> getCategories( CategoryCallback categoryCallback);
    MutableLiveData<Global> getGlobal(GlobalCallback globalCallback);

     interface QuiestionCallback extends BaseQuestionCallback<List<Question>> {}

    interface CategoryCallback extends  BaseQuestionCallback<List<Category>>{}

     interface GlobalCallback extends  BaseQuestionCallback<Global>{}

}
