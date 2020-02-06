package com.geektech.quizapp_gt_4_2.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    private TextView tvResult;
    private static String ID_QUESTION= "id";
    private int idQquestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvResultTittle =findViewById(R.id.card_tittle);
        tvResultDifficulty = findViewById(R.id.card_diff_all);
        tvResultScore = findViewById(R.id.card_score);
        tvResult = findViewById(R.id.card_result_percent);
        idQquestion = getIntent().getIntExtra(ID_QUESTION,0);


    }

    public static void startResult(Context context, int id){
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(ID_QUESTION, id);
        context.startActivity(intent);
    }

    public void onClickFinish(View view) {
    }
}
