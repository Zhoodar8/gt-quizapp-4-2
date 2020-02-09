package com.geektech.quizapp_gt_4_2.presentation.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.presentation.quiz.QuizActivity;
import com.geektech.quizapp_gt_4_2.utils.OnSimpleItemSelectedListener;
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

    private void viewsListener() {
        seekBar.setOnSeekBarChangeListener(new OnSimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                textQuestionCount.setText("" + progress);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spinnerCategory = 0;
                getSpinerPosition();
                if (spinnerCat.getSelectedItemPosition() !=0){
                    spinnerCategory = spinnerCat.getSelectedItemPosition() + 8;
                }
             QuizActivity.start(getActivity(),seekBar.getProgress(), spinnerCategory
                     ,difficult);

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);
        mViewModel.message.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("ololo", s);
            }
        });
        mViewModel.onLoginClick();
    }
    private void getSpinerPosition() {
        spinnerDiff.setOnItemSelectedListener(new OnSimpleItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                super.onItemSelected(parent, view, position, id);
                switch (position) {
                    case 1:
                        difficult = null;
                        break;
                    case 2:
                        difficult = "easy";
                        break;
                    case 3:
                        difficult = "medium";
                        break;
                    case 4:
                        difficult = "hard";
                        break;
                }
            }
        });
    }


    }

