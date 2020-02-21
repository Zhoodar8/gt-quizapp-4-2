package com.geektech.quizapp_gt_4_2.presentation.quiz;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreActivity;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.presentation.quiz.recycler.QuizAdapter;
import com.geektech.quizapp_gt_4_2.presentation.quiz.recycler.QuizViewHolder;
import com.geektech.quizapp_gt_4_2.presentation.result.ResultActivity;

import java.util.List;

public class QuizActivity extends CoreActivity implements QuizViewHolder.Listener {

    //region Static
    private TextView tvQuestionCategory;
    private TextView tvCountQuestion;
    private ProgressBar progressBar;
    private Button buttonSkip;
    private RecyclerView recyclerView;
    private QuizViewModel quizViewModel;
    private LottieAnimationView lottieAnimation;

    private static String EXTRA_AMOUNT = "amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficulty";
    private QuizAdapter adapter;

    private int amount;
    private Integer category;
    private String difficulty;
//endregion

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        lottieAnimation =findViewById(R.id.lottie);
        tvQuestionCategory = findViewById(R.id.quiz_text_tittle);
        tvCountQuestion = findViewById(R.id.quiz_txt_count);
        progressBar = findViewById(R.id.quiz_progess_bar);
        buttonSkip = findViewById(R.id.quiz_btn_answer);
        recyclerView = findViewById(R.id.quiz_recycler);
        adapter = new QuizAdapter(this::onAnswerClick);
        LinearLayoutManager manager = new LinearLayoutManager(QuizActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_quiz;
    }

    public static void start(Context context, int amount, int category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(EXTRA_CATEGORY, category);
        context.startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);
        initViews();
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 0);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        Log.e("--------", "QuizActivity  " + difficulty + " " + amount);
        if (category == 0) {
            category = null;
        }
        quizViewModel.init(amount, category, difficulty);
        showData();
        quizViewModel.question.observe(this, questions -> {
            recyclerView.setVisibility(View.VISIBLE);
            adapter.update(questions);

        });
        quizViewModel.positionOfQuestion.observe(this, integer -> {
            tvQuestionCategory.setVisibility(View.VISIBLE);
            tvCountQuestion.setVisibility(View.VISIBLE);
            lottieAnimation.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            buttonSkip.setVisibility(View.VISIBLE);

            tvQuestionCategory.setText(adapter.getmQuestions().get(integer).getCategory());
            tvCountQuestion.setText(integer +1 + "/" + amount);
            recyclerView.scrollToPosition(integer);
         //   recyclerView.smoothScrollToPosition(integer);
            progressBar.setProgress(integer);
            progressBar.setMax(amount-1);
        });

        quizViewModel.finish.observe(this, aBoolean -> finish());
        quizViewModel.finishEvent.observe(this, aVoid -> {
            finish();
        });
        quizViewModel.openResultEvent.observe(this, integer ->
                ResultActivity.startResult(QuizActivity.this,integer));
    }

    private void showData() {
        quizViewModel.question.observe(this, questions -> { adapter.update(questions);
            Log.e("-----------", "Adapter" + questions);
        });
    }

    public void onClickSkip(View view) {
        quizViewModel.onSkipClick();
    }

    public void onClickBack(View view) {
        quizViewModel.onBackPressed();
    }
    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
           quizViewModel.onAnswerClick(position,selectedAnswerPosition);
    }
}