package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.core.CoreCallback;
import com.geektech.quizapp_gt_4_2.data.remote.response.QuizQuestinResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(QuiestionsCallback callback) {
         Call<QuizQuestinResponse> call = client.getQuestions(
                 20,
                 null,
                 null);

         call.enqueue(new CoreCallback<QuizQuestinResponse>() {
             @Override
             public void onSuccess(QuizQuestinResponse result) {
                 callback.onSuccess(result.getResults());
             }

             @Override
             public void onFailure(Exception e) {
                 callback.onFailure(e);
             }
         });
    }


    private interface QuizApi {
       // EndPoint
        @GET("api.php")
        Call<QuizQuestinResponse> getQuestions(@Query("amount")int amount,
                                               @Query("category")String category,
                                               @Query("difficulty")String difficulty);

    }
}
