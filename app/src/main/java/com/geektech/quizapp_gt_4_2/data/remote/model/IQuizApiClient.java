package com.geektech.quizapp_gt_4_2.data.remote.model;

import androidx.lifecycle.MutableLiveData;

import com.geektech.quizapp_gt_4_2.core.BaseCallback;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestinResponse;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestionsCount;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.model.Global;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuestionsCount;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(int amount, Integer category, String difficulty, QuiestionCallback callback);
    void getCategories( CategoryCallback categoryCallback);
    void getGlobal(GlobalCallback globalCallback);
    void getQuestionsCount(Integer category, CountCallbak countCallbak);

    interface QuiestionCallback extends BaseCallback<List<Question>> {}

    interface CategoryCallback extends BaseCallback<List<Category>> {}

     interface GlobalCallback extends BaseCallback<Global> {}

     interface CountCallbak extends  BaseCallback<QuizQuestionsCount>{}

}
