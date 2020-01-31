package com.geektech.quizapp_gt_4_2.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.model.Global;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

import javax.security.auth.login.LoginException;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> question = new MutableLiveData<>();

    public void getQuestion(int amount, Integer category, String difficulty){
        App.iQuizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuiestionCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                Log.e("--------","QuizViewModel" + questions.toString());
                question.setValue(questions);
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getCategories(){
        App.iQuizApiClient.getCategories(new IQuizApiClient.CategoryCallback() {
            @Override
            public void onSuccess(List<Category> categories) {
                Log.e("--------", categories.toString());
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getGlobals(){
        App.iQuizApiClient.getGlobal(new IQuizApiClient.GlobalCallback() {
            @Override
            public void onSuccess(Global global) {
                Log.e("--------", global.getTotalNumOfQuestions());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}
