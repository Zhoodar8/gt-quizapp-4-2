package com.geektech.quizapp_gt_4_2.data.history;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(QuizResult quizResult);


    @Query("DELETE FROM quiz_result WHERE id=:id")
    void delete(int id);

    @Query("SELECT * FROM quiz_result WHERE id=:id")
    QuizResult get(int id);


    @Query("SELECT * FROM QUIZ_RESULT")
    LiveData<List<QuizResult>> getAll();


    @Query("DELETE FROM QUIZ_RESULT")
    void deleteAll();


}
