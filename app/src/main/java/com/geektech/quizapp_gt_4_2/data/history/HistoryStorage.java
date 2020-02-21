package com.geektech.quizapp_gt_4_2.data.history;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private HistoryDao dao;

    public HistoryStorage(HistoryDao historyDao) {
        this.dao =historyDao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return dao.get(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) dao.insert(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(), quizResult ->{
            ArrayList<History> histories = new ArrayList<>();
            for (QuizResult result: quizResult) {
                History  histResult = new History(
                        result.getId(),
                        result.getDifficulty(),
                        result.getCategory(),
                        result.getCorrectAnswer(),
                        result.getQuestions().size(),
                        result.getCreatedAt()
                );
                histories.add(histResult);
            }

            return histories;
        });
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
          dao.deleteAll();
    }


}
