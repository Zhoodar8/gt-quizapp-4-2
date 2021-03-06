package com.geektech.quizapp_gt_4_2.data;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient, IHistoryStorage {
    private IQuizApiClient apiClient;
    private IHistoryStorage historyStorage;


    public QuizRepository(IQuizApiClient client,
                          IHistoryStorage storage) {
        apiClient = client;
        historyStorage = storage;
    }

    public void getQuestions(int amount, Integer category, String difficulty, IQuizApiClient.QuiestionCallback callback) {
        apiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuiestionCallback() {

            @Override
            public void onSuccess(List<Question> result) {
                for (Question answer : result) {
                    List<String> answers = new ArrayList<>();
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

    @Override
    public void getCategories(CategoryCallback categoryCallback) {

    }

    @Override
    public void getGlobal(GlobalCallback globalCallback) {

    }

    @Override
    public void getQuestionsCount(Integer category, CountCallbak countCallbak) {

    }

    //iHistory interface methods
    @Override
    public QuizResult getQuizResult(int id) {
        return historyStorage.getQuizResult(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return historyStorage.saveQuizResult(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return historyStorage.getAllHistory();
    }

    @Override
    public void delete(int id) {
        historyStorage.delete(id);

    }

    @Override
    public void deleteAll() {
        historyStorage.deleteAll();
    }
}