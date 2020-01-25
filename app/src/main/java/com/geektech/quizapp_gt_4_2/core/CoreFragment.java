package com.geektech.quizapp_gt_4_2.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geektech.quizapp_gt_4_2.R;

public abstract class CoreFragment extends Fragment {

    protected abstract int getLayoutID();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(getLayoutID(), container, false);
        return view;}

}
