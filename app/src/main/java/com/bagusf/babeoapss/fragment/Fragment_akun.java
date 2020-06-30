package com.bagusf.babeoapss.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bagusf.babeoapss.Activity_tentang;
import com.bagusf.babeoapss.R;
import com.bagusf.babeoapss.akun.LoginActivity;
import com.bagusf.babeoapss.riwayat;
import com.bagusf.babeoapss.utils.SessionManager;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_akun extends Fragment {
    SessionManager sessionManager;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.btnRiwayat)
    TextView btnRiwayat;
    @BindView(R.id.btnlogout)
    TextView btnlogout;
    @BindView(R.id.Linear_akun1)
    LinearLayout LinearAkun1;
    @BindView(R.id.l1)
    CircularImageView l1;
    @BindView(R.id.tentang)
    TextView tentang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_akun, container, false);
        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getActivity());
        txt1.setText(sessionManager.getUsername());
        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_tentang.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logoutUser();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), riwayat.class);
                startActivity(intent);

            }
        });
        return view;
    }
}
