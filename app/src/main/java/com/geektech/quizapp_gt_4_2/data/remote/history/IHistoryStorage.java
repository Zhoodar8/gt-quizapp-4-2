package com.geektech.quizapp_gt_4_2.data.remote.history;




public interface IHistoryStorage {



    void save();



    void delete();



    void deleteAll();


    void get();

    void getAll();
}
