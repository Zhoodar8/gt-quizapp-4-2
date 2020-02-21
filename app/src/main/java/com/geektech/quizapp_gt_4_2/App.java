package com.geektech.quizapp_gt_4_2;

import android.app.Application;

import androidx.room.Room;

import com.geektech.quizapp_gt_4_2.data.QuizRepository;
import com.geektech.quizapp_gt_4_2.data.db.QuizDatabase;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.QuizApiClient;
import com.geektech.quizapp_gt_4_2.data.history.HistoryStorage;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;

public class App extends Application {

  public static IQuizApiClient iQuizApiClient;
  public static IHistoryStorage historyStorage;
  public static QuizRepository quizRepository;
  public static QuizDatabase quizDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        // you can use it like this //
//        QuizRepository repository = new QuizRepository(
//                new QuizApiClient(),
//                new HistoryStorage(quizDatabase.historyDao())
//        );
//
//        quizApiClient = repository;
//        historyStorage = repository;


        quizDatabase = Room.databaseBuilder(
                this,
                QuizDatabase.class,
                "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        iQuizApiClient =new QuizApiClient();
        historyStorage =new HistoryStorage(quizDatabase.historyDao());

        quizRepository =new QuizRepository(
                iQuizApiClient,
                historyStorage);


    }

}
