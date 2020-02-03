package com.geektech.quizapp_gt_4_2.data.remote.history;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface IHistoryStorage {


    @Insert
    void insert();


    @Delete
    void delete();


    @Delete
    void deleteAll();
}
