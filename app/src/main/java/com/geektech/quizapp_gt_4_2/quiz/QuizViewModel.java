package com.geektech.quizapp_gt_4_2.quiz;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.model.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestionsCount;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.model.Global;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    // reUse
     private IQuizApiClient iQuizApiClient = App.iQuizApiClient;
    MutableLiveData<List<Question>> question = new MutableLiveData<>();
    MutableLiveData<Integer> position =  new MutableLiveData<>();
    MutableLiveData<Boolean> finish = new MutableLiveData<>();
    private List<Question> mQuestion;
    private int counter;

    public QuizViewModel(){
        counter = 0;
    }

    public void getQuestion(@Nullable int amount, Integer category, String difficulty){
          //reUse
       // iQuizApiClient.getQuestions();
        App.quizRepository.getQuestions(amount, category, difficulty, new IQuizApiClient.QuiestionCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                Log.e("--------","QuizViewModel" + result.toString());
                mQuestion =result;
                question.setValue(mQuestion);
                position.setValue(0);
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("--------","getQuestion  "+ e.getLocalizedMessage());
            }
        });
    }

    public void getCategories(){
        App.iQuizApiClient.getCategories( new IQuizApiClient.CategoryCallback() {
            @Override
            public void onSuccess(List<Category> result) {
                Log.e("--------", result.toString());
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("--------","getCategories  " + e.getLocalizedMessage());
            }
        });
    }

    public void getGlobals(){
        App.iQuizApiClient.getGlobal(new IQuizApiClient.GlobalCallback() {
            @Override
            public void onSuccess(Global result) {
                Log.e("--------", result.getTotalNumOfQuestions());
            }

            @Override
            public void onFailure(Exception e) {
                    Log.e("--------","getGlobals  "+ e.getLocalizedMessage());
            }
        });
    }


    public void getQuestionsCount(Integer category){
        App.iQuizApiClient.getQuestionsCount(category, new IQuizApiClient.CountCallbak() {
            @Override
            public void onSuccess(QuizQuestionsCount result) {
                Log.e("------", "QuestionCount  " + result.getCategoryQuestionCount());
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("--------","getQuestionsCount  "+ e.getLocalizedMessage());
            }
        });
    }


    private int getCorrectAnswersAmount() {
        //TODO:
        return 0;
    }

    void finishQuiz() {
        //TODO:
    }





    public void onSkipClick(){
        if (position != null ) {
            position.setValue(++counter);
        }else {

        }
     }

     public void onBackPressed(){
         Integer currentPosition = position.getValue();
         if (currentPosition != null && currentPosition ==0 ){
             finish.setValue(true);
         }else if (currentPosition != null && currentPosition > 0){
             position.setValue(--counter);
         }
     }

     void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestion.size() >position && position >= 0) {
            mQuestion.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
            question.setValue(mQuestion);
            onSkipClick();
        }
    }
}
