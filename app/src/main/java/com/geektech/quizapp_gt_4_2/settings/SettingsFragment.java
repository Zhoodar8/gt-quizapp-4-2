package com.geektech.quizapp_gt_4_2.settings;

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
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.main.MainViewModel;

import java.security.Provider;

public class SettingsFragment extends Fragment {
    private SettingsViewModel mViewModel;
    private MainViewModel mMainViewModel;
    private TextView txt_Result;
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        txt_Result = view.findViewById(R.id.txt_Result);
        return view;
    }

    private void getCount() {
        mMainViewModel.countList.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String s = String.valueOf(integer);
                txt_Result.setText(s);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        getCount();

        // TODO: Use the ViewModel
    }

}
