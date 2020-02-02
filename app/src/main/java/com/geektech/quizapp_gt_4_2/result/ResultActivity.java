package com.geektech.quizapp_gt_4_2.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreActivity;

public class ResultActivity extends CoreActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void startResult(Context context){
        Intent intent = new Intent(context, ResultActivity.class);
        context.startActivity(intent);
    }
}
