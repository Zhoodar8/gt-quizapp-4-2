package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.core.SingleLiveEvent;
import com.geektech.quizapp_gt_4_2.data.remote.model.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    // reUse
     private IQuizApiClient iQuizApiClient = App.iQuizApiClient;

    MutableLiveData<List<Question>> question = new MutableLiveData<>();
    MutableLiveData<Integer> positionOfQuestion =  new MutableLiveData<>();
    MutableLiveData<Boolean> finish = new MutableLiveData<>();

    SingleLiveEvent<Long> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    private List<Question> mQuestion;
    private int counter;
    private int id =0;


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
                positionOfQuestion.setValue(0);
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("--------","getQuestion  "+ e.getLocalizedMessage());
            }
        });
    }






    private int getCorrectAnswersAmount() {

        int correctAnswer = 0;

        for (int i = 0; i <= mQuestion.size() -1 ; i++) {
            if (mQuestion.get(i).getSelectedAnswerPosition()!=-1) {
                String corAnswer = mQuestion.get(i).getCorrectAnswer();
                String selectedAnswer = mQuestion.get(i).getAnswers()
                        .get(mQuestion.get(i).getSelectedAnswerPosition());
                if (corAnswer.equals(selectedAnswer)) {
                    correctAnswer++;
                }
            }
        }



        return correctAnswer;
    }

    void finishQuiz() {

        QuizResult quizResult = new QuizResult(
                id,
                 mQuestion,
                getCorrectAnswersAmount(),
                new Date(),
                getCategory(),
                getDifficulty()

        );
        //TODO: Start Result activity

     long resultId = App.historyStorage.save(quizResult);
     finishEvent.call();
     openResultEvent.setValue(resultId);

    }


       private String getCategory(){
        String category ="mixed";
        if (mQuestion.get(0).getCategory().equals(mQuestion.get(1).getCategory())){

            category = mQuestion.get(0).getCategory();

           }
        return category;
       }

       private String getDifficulty(){
        String difficulty ="all";
        if (mQuestion.get(0).getDifficulty().equals(mQuestion.get(1).getDifficulty())){
            difficulty = mQuestion.get(0).getDifficulty().toString();
        }
        return difficulty;
       }


    public void onSkipClick(){
        if (positionOfQuestion.getValue() != null && positionOfQuestion.getValue() < mQuestion.size()-1 ) {
            mQuestion.get(positionOfQuestion.getValue()).setSelectedAnswerPosition(-1);
            positionOfQuestion.setValue(++counter);

            Log.e("----", "onSkipClick: " + positionOfQuestion.getValue() );
        }else if (positionOfQuestion.getValue() != null &&  positionOfQuestion.getValue() == mQuestion.size() -1){
            mQuestion.get(positionOfQuestion.getValue()).setSelectedAnswerPosition(-1);
           finishQuiz();
        }
     }

     public void onBackPressed(){
         Integer currentPosition = positionOfQuestion.getValue();
         if (currentPosition != null && currentPosition ==0 ){
             finish.setValue(true);
         }else if (currentPosition != null && currentPosition > 0){
             positionOfQuestion.setValue(--counter);
         }
     }

     void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestion.size() >position && position >= 0) {
            mQuestion.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
            question.setValue(mQuestion);
            positionOfQuestion.setValue(++counter);

        }

        if (position == mQuestion.size() -1 ){
            finishQuiz();
        }

    }
}
