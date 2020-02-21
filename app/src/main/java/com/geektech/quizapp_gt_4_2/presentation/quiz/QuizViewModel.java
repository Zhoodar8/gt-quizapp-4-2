package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.core.SingleLiveEvent;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    // reUse
    //  private IQuizApiClient iQuizApiClient = App.iQuizApiClient;
    private List<Question> mQuestion;
    private int counter;
    private String mDifficulty;
    private String mCategory;

    MutableLiveData<List<Question>> question = new MutableLiveData<>();
    MutableLiveData<Integer> positionOfQuestion = new MutableLiveData<>();
    MutableLiveData<Boolean> finish = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoad = new MediatorLiveData<>();
    SingleLiveEvent<Boolean> isFailure = new SingleLiveEvent<>();

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();


    public QuizViewModel() {
        counter = 0;
    }

    void init(@Nullable int amount, Integer category, String difficulty) {
        //reUse
        // iQuizApiClient.getQuestions();
        isLoad.setValue(true);
        App.quizRepository.getQuestions(amount, category, difficulty, new IQuizApiClient.QuiestionCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                Log.e("--------", "QuizViewModel" + result.toString());
                    isLoad.setValue(false);
                    mQuestion = result;
                    question.setValue(mQuestion);
                    positionOfQuestion.setValue(0);
                    if (category != null && result.size() > 0) {
                        mCategory = mQuestion.get(0).getCategory();
                    } else {
                        mCategory = "Mixed";
                    }
                    if (difficulty != null && result.size() > 0) {
                        mDifficulty = mQuestion.get(0).getDifficulty().toString();
                    } else {
                        mDifficulty = "All";
                    }


            }



            @Override
            public void onFailure(Exception e) {
                Log.e("--------", "init  " + e.getLocalizedMessage());
                isLoad.setValue(false);
            }
        });
    }


    void onBackPressed() {
        Integer currentPosition = positionOfQuestion.getValue();
        if (currentPosition != null && currentPosition == 0) {
            finish.setValue(true);
        } else if (currentPosition != null && currentPosition > 0) {
            positionOfQuestion.setValue(--counter);
        }
    }

    void onSkipClick() {
        if (positionOfQuestion.getValue() != null && positionOfQuestion.getValue() < mQuestion.size() - 1) {
            mQuestion.get(positionOfQuestion.getValue()).setSelectedAnswerPosition(-1);
            positionOfQuestion.setValue(++counter);

            Log.e("----", "onSkipClick: " + positionOfQuestion.getValue());
        } else if (positionOfQuestion.getValue() != null && positionOfQuestion.getValue() == mQuestion.size() - 1) {
            mQuestion.get(positionOfQuestion.getValue()).setSelectedAnswerPosition(-1);
            finishQuiz();
        }
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {
        Log.e("-------", mQuestion.size()+"-" + position);
        if (mQuestion.size()-1 > position && position >= 0) {
            mQuestion.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
            question.setValue(mQuestion);
            positionOfQuestion.setValue(++counter);
        } else if (position  == mQuestion.size()-1) {
            finishQuiz();
        }

    }

    void finishQuiz() {
        QuizResult quizResult = new QuizResult(
                0,
                mQuestion,
                getCorrectAnswersAmount(),
                new Date(),
                mCategory,
                mDifficulty

        );
        //TODO: Start Result activity

        int resultId = App.historyStorage.saveQuizResult(quizResult);
        Log.e("==========ID", App.historyStorage.getAll() + "");

        finishEvent.call();
        openResultEvent.setValue(resultId);

    }


    private int getCorrectAnswersAmount() {

        int correctAnswer = 0;



        for (int i = 0; i < mQuestion.size()-1 ; i++) {
            Log.e("----ii", mQuestion.get(i).getSelectedAnswerPosition() +" SIZE");
            if (mQuestion.get(i).getSelectedAnswerPosition() != -1) {
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



}
