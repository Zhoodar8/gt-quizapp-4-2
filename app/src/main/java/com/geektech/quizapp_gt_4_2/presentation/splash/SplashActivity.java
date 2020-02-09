package com.geektech.quizapp_gt_4_2.presentation.splash;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreActivity;
import com.geektech.quizapp_gt_4_2.presentation.main.MainActivity;

public class SplashActivity extends CoreActivity {
    private ImageView img;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        img =findViewById(R.id.splash_img);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.start(SplashActivity.this);
                finish();
            }
        },1000);

    }
}
