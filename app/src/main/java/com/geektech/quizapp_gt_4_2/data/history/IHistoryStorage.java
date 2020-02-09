package com.geektech.quizapp_gt_4_2.data.history;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.List;

@Dao
public interface IHistoryStorage {

    @Insert
    long save(QuizResult result);

    @Delete
    void delete(QuizResult result);

    @Query("DELETE FROM QUIZ_RESULT")
    void deleteAll();

    @Query("SELECT * FROM quiz_result WHERE id=:id")
    QuizResult get(int id);

    @Query("SELECT * FROM QUIZ_RESULT ")
    LiveData<List<QuizResult>> getAll();
}
