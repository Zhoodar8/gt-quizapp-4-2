package com.geektech.quizapp_gt_4_2.data;

import com.geektech.quizapp_gt_4_2.data.remote.model.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository {
    private IQuizApiClient apiClient;
    private IHistoryStorage historyStorage;


    public QuizRepository(IQuizApiClient client,
                          IHistoryStorage storage) {
        apiClient =client;
        historyStorage =storage;
    }

    public void getQuestions(int amount, Integer category, String difficulty, IQuizApiClient.QuiestionCallback callback) {
        apiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuiestionCallback(){

            @Override
            public void onSuccess(List<Question> result) {

              for (Question answer : result){
                  List<String>  answers = new ArrayList<>();
                  answers.add(answer.getCorrectAnswer());
                  answers.addAll(answer.getIncorrectAnswers());
                  Collections.shuffle(answers);
                  answer.setAnswers(answers);

              }

                //TODO: Shuffle result answers
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                 callback.onFailure(e);
            }
        });


    }
}
