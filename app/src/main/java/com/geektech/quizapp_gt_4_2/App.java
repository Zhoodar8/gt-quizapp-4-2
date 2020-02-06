package com.geektech.quizapp_gt_4_2;

import android.app.Application;

import com.geektech.quizapp_gt_4_2.data.QuizRepository;
import com.geektech.quizapp_gt_4_2.data.remote.model.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizApiClient;
import com.geektech.quizapp_gt_4_2.data.history.HistoryStorage;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;

public class App extends Application {

  public static IQuizApiClient iQuizApiClient;
  public static IHistoryStorage historyStorage;
  public static QuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        iQuizApiClient =new QuizApiClient();
        historyStorage =new HistoryStorage();

        quizRepository =new QuizRepository(
                iQuizApiClient,
                historyStorage);

    }

}
