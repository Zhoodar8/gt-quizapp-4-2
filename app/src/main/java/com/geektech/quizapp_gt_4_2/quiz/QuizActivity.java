package com.geektech.quizapp_gt_4_2.quiz;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreActivity;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.quiz.quiz_recycler.QuizAdapter;

import java.util.List;

public class QuizActivity extends CoreActivity {
    private TextView tvTittleQuestion;
    private TextView tvCountQuestion;
    private ProgressBar progressBar;
    private Button buttonSkip;
    private RecyclerView recyclerView;
    private QuizAdapter adapter = new QuizAdapter();
    private int amount;
    private Integer category;
    private String difficulty;
    private QuizViewModel quizViewModel;

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        tvTittleQuestion = findViewById(R.id.quiz_text_tittle);
        tvCountQuestion = findViewById(R.id.quiz_txt_count);
        progressBar = findViewById(R.id.quiz_progess_bar);
        buttonSkip = findViewById(R.id.quiz_btn_answer);
        recyclerView = findViewById(R.id.quiz_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(QuizActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_quiz;
    }

    public static void start(Context context, int amount, int category, String difficulty){
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra("seekbar", amount);
        intent.putExtra("diff", difficulty);
        intent.putExtra("cat", category);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);
        initViews();
        amount = getIntent().getIntExtra("seekbar", 0);
        category = getIntent().getIntExtra("cat",0);

        difficulty = getIntent().getStringExtra("diff");

        Log.e("--------","QuizActivity  " +difficulty+" " + amount);
        if (category == 0){
            category = null;
        }
        quizViewModel.getQuestion(amount,category,difficulty);
        showData();
        setCategory();

    }

    private void setCategory() {
        App.iQuizApiClient.getCategories(new IQuizApiClient.CategoryCallback() {
            @Override
            public void onSuccess(List<Category> categories) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void showData() {
        quizViewModel.question.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.update(questions);
                Log.e("-----------", "Adapter"+ questions);
            }
        });
    }


}