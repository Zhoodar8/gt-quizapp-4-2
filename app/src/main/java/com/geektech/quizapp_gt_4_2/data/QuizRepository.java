package com.geektech.quizapp_gt_4_2.data;

import com.geektech.quizapp_gt_4_2.data.remote.model.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.history.IHistoryStorage;

public class QuizRepository {
    private IQuizApiClient apiClient;
    private IHistoryStorage historyStorage;

    public QuizRepository(IQuizApiClient client,
                          IHistoryStorage storage) {
        apiClient =client;
        historyStorage =storage;
    }
}
