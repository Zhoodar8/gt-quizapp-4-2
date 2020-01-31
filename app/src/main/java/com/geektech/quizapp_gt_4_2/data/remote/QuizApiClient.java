package com.geektech.quizapp_gt_4_2.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.geektech.quizapp_gt_4_2.core.CoreCallback;
import com.geektech.quizapp_gt_4_2.data.remote.response.QuizCategoryResponse;
import com.geektech.quizapp_gt_4_2.data.remote.response.QuizGlobalResponse;
import com.geektech.quizapp_gt_4_2.data.remote.response.QuizQuestinResponse;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.model.Global;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

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
    public MutableLiveData<Question> getQuestions(int amount, Integer category, String difficulty, QuiestionCallback callback) {
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

            }
        });
        return null;
    }

    @Override
    public MutableLiveData<Category> getCategories(CategoryCallback categoryCallback) {
       Call<QuizCategoryResponse> call = client.getCategories();
       call.enqueue(new CoreCallback<QuizCategoryResponse>() {
           @Override
           public void onSuccess(QuizCategoryResponse result) {
               categoryCallback.onSuccess(result.getTriviaCategories());
           }

           @Override
           public void onFailure(Exception e) {

           }
       });
       return null;
    }

    @Override
    public MutableLiveData<Global> getGlobal(GlobalCallback globalCallback) {
      Call<QuizGlobalResponse> call = client.getGlobals();
      call.enqueue(new CoreCallback<QuizGlobalResponse>() {
          @Override
          public void onSuccess(QuizGlobalResponse result) {
              globalCallback.onSuccess(result.getGlobal());
          }

          @Override
          public void onFailure(Exception e) {

          }
      });
      return null;
    }


    private interface QuizApi {
       // EndPoint
        @GET("api.php")
        Call<QuizQuestinResponse> getQuestions(@Query("amount")int amount,
                                               @Query("category")Integer category,
                                               @Query("difficulty")String difficulty);

        @GET("api_count_global.php")
        Call<QuizGlobalResponse> getGlobals();

        @GET("api_category.php")
        Call<QuizCategoryResponse> getCategories();
    }


}
