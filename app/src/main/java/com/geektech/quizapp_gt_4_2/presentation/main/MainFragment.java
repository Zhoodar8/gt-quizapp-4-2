package com.geektech.quizapp_gt_4_2.presentation.main;


import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.presentation.quiz.QuizActivity;
import com.geektech.quizapp_gt_4_2.utils.OnSimpleSeekBarChangeListener;

public class MainFragment extends CoreFragment {
    @Override
    protected int getLayoutID() {
        return R.layout.main_fragment;
    }

    private MainViewModel mViewModel;
    private SeekBar seekBar;
    private TextView textQuestionCount;
    private Button btnStart;
    private String difficult;
    private Spinner spinnerCat;
    private Spinner spinnerDiff;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar = view.findViewById(R.id.seek_bar);
        btnStart = view.findViewById(R.id.btn_start);
        textQuestionCount = view.findViewById(R.id.txt_q_count);
        spinnerCat = view.findViewById(R.id.spinner_category);
        spinnerDiff = view.findViewById(R.id.spinner_difficulty);
        seekBar.setPadding(21, 0, 21, 0);
        viewsListener();
    }

    @SuppressLint("LongLogTag")
    private void viewsListener() {
        seekBar.setOnSeekBarChangeListener(new OnSimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                textQuestionCount.setText("" + progress);
            }
        });

        btnStart.setOnClickListener(v -> {
            spinnerDiff.getSelectedItemPosition();
            int spinnerCategory = 0;
            if (spinnerCat.getSelectedItemPosition() != 0) {
                spinnerCategory = spinnerCat.getSelectedItemPosition() + 8;
                Log.e("-------Spinner Category", spinnerCategory + ""); }
            QuizActivity.start(getActivity(), seekBar.getProgress(), spinnerCategory
                    , getDifficult());
            Log.e("-------SpinnerDifficulty", getDifficult() + "");


        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);
    }

    private String getDifficult() {
        switch (spinnerDiff.getSelectedItemPosition()) {
            case 0:
                difficult = null;
                break;
            case 1:
                difficult = getString(R.string.easy);
                break;
            case 2:
                difficult = getString(R.string.medium);
                break;
            case 3:
                difficult = getString(R.string.hard);
                break;
        }
        return difficult;
    }


}

