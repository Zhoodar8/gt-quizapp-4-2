package com.geektech.quizapp_gt_4_2.presentation.result;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreActivity;

public class ResultActivity extends CoreActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_result;
    }

    private TextView tvResultTittle;
    private TextView tvResultDifficulty;
    private TextView tvResultScore;
    private TextView tvResultPercent;
    private ResultViewModel resultViewModel;
    private static String EXTRA_RESULT_ID = "id";
    private int idQquestion;
    private LottieAnimationView lottie;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultViewModel = ViewModelProviders.of(this)
                .get(ResultViewModel.class);
        tvResultTittle = findViewById(R.id.card_tittle);
        tvResultDifficulty = findViewById(R.id.card_diff_all);
        tvResultScore = findViewById(R.id.card_score);
        tvResultPercent = findViewById(R.id.card_result_percent);
        idQquestion = getIntent().getIntExtra(EXTRA_RESULT_ID, 0);
        lottie = findViewById(R.id.lottie_res);
       resultViewModel.getResult(idQquestion);
        resultViewModel.historyResult.observe(this, quizResult -> {
            Log.e("lolololo", quizResult.getCategory());
            tvResultTittle.setText("Category: " +  quizResult.getCategory());
            tvResultDifficulty.setText(quizResult.getDifficulty());
            Log.e("lolololo", quizResult.getDifficulty());
            tvResultScore.setText(quizResult.getCorrectAnswer()+"/"+ quizResult.getQuestions().size());
            Log.e("lolololo", quizResult.getCorrectAnswer()+"/"+ quizResult.getQuestions().size());
            int correctAnswerPercent = (int)((double) quizResult.getCorrectAnswer()/quizResult.getQuestions().size() *100);
            tvResultPercent.setText(correctAnswerPercent + "%");
            Log.e("lolololo", correctAnswerPercent +" %");
        });
    }

    public static void startResult(Context context, int id) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(EXTRA_RESULT_ID, id);
        context.startActivity(intent);
    }

    public void onClickFinish(View view) {
        finish();
    }
}
