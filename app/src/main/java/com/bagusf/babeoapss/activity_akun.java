package com.bagusf.babeoapss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bagusf.babeoapss.akun.LoginActivity;
import com.bagusf.babeoapss.akun.activity_registrasi;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class activity_akun extends AppCompatActivity {

    @BindView(R.id.l1)
    CircularImageView l1;
    @BindView(R.id.txt1)
    TextView txt1;
//    @BindView(R.id.btn_riwayat)
//    Button btnRiwayat;
    @BindView(R.id.tentang)
    TextView tentang;
    @BindView(R.id.Linear_akun1)
    LinearLayout LinearAkun1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        ButterKnife.bind(this);

//        btnRiwayat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(activity_akun.this, riwayat.class);
//                startActivity(intent);
//            }
//        });
    }
}
