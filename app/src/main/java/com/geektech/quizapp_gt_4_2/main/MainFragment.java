package com.geektech.quizapp_gt_4_2.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;

public class MainFragment extends CoreFragment {
    @Override
    protected int getLayoutID() {
        return R.layout.main_fragment;
    }

    private MainViewModel mViewModel;
    private SeekBar seekBar;
    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar = view.findViewById(R.id.seek_bar);
        seekBar.setProgress(0);
        seekBar.setMax(50);
        seekBar.setPadding(21,0,21,0);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);
        mViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("ololo", s);
            }
        });
        mViewModel.onLoginClick();
    }


}
