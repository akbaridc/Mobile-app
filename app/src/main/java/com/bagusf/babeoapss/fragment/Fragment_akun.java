package com.bagusf.babeoapss.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.bagusf.babeoapss.R;

import butterknife.ButterKnife;

public class Fragment_akun extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_akun, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
