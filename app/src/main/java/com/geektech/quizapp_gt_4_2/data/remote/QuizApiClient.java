package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.core.CoreCallback;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizCategoryResponse;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizGlobalResponse;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestinResponse;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestionsCount;

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
    TriviaApi client = retrofit.create(TriviaApi.class);

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, QuiestionCallback callback) {
        Call<QuizQuestinResponse> call = client.getQuestions(amount,
                category,
                difficulty);
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

    @Override
    public void getCategories(CategoryCallback categoryCallback) {
       Call<QuizCategoryResponse> call = client.getCategories();
       call.enqueue(new CoreCallback<QuizCategoryResponse>() {
           @Override
           public void onSuccess(QuizCategoryResponse result) {
               categoryCallback.onSuccess(result.getTriviaCategories());
           }

           @Override
           public void onFailure(Exception e) {
                categoryCallback.onFailure(e);
           }
       });

    }

    @Override
    public void getGlobal(GlobalCallback globalCallback) {
      Call<QuizGlobalResponse> call = client.getGlobals();
      call.enqueue(new CoreCallback<QuizGlobalResponse>() {
          @Override
          public void onSuccess(QuizGlobalResponse result) {
              globalCallback.onSuccess(result.getGlobal());
          }

          @Override
          public void onFailure(Exception e) {
                globalCallback.onFailure(e);
          }
      });

    }

    @Override
    public void getQuestionsCount(Integer category, CountCallbak countCallbak) {
        Call<QuizQuestionsCount> call =client.getQuestionsCount(category);
        call.enqueue(new CoreCallback<QuizQuestionsCount>() {
            @Override
            public void onSuccess(QuizQuestionsCount result) {
                countCallbak.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
               countCallbak.onFailure(e);
            }
        });
    }


    private interface TriviaApi {
       // EndPoint
        @GET("api.php")
        Call<QuizQuestinResponse> getQuestions(@Query("amount")int amount,
                                               @Query("category")Integer category,
                                               @Query("difficulty")String difficulty);

        @GET("api_count_global.php")
        Call<QuizGlobalResponse> getGlobals();

        @GET("api_category.php")
        Call<QuizCategoryResponse> getCategories();

        @GET("api_count.php")
        Call<QuizQuestionsCount> getQuestionsCount(
                @Query("category")Integer category
        );
    }


}
