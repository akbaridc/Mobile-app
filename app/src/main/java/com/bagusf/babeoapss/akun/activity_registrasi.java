package com.bagusf.babeoapss.akun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bagusf.babeoapss.JavaNavigation;
import com.bagusf.babeoapss.R;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bagusf.babeoapss.utils.network.URL_REGISTER;

public class activity_registrasi extends AppCompatActivity {
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String result;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.eTxtNamaLengkap)
    EditText eTxtNamaLengkap;
    @BindView(R.id.eTxtjk)
    EditText eTxtjk;
    @BindView(R.id.eTxtAlamat)
    EditText eTxtAlamat;

    @BindView(R.id.eTxtTelepon)
    EditText eTxtTelepon;
    @BindView(R.id.eTxtEmail)
    EditText eTxtEmail;
    @BindView(R.id.eTxtUsername)
    EditText eTxtUsername;
    @BindView(R.id.eTxtPassword)
    EditText eTxtPassword;
    @BindView(R.id.btn_daftar)
    Button btnDaftar;
    @BindView(R.id.login)
    TextView login;
    private EditText nama, pass, tl, jk, telp, alamat, username, email;
    private Button btn_regis;
    private TextView link_regist;
    private ProgressBar progressBar;
    ProgressDialog progressDialog;
    private static final String TAG = "MainActivity"; //untuk melihat log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        ButterKnife.bind(this);
        nama = findViewById(R.id.eTxtNamaLengkap);
        username = findViewById(R.id.eTxtUsername);
        pass = findViewById(R.id.eTxtPassword);

//        hidden = findViewById(R.id.idhiden);
        telp = findViewById(R.id.eTxtTelepon);
        email = findViewById(R.id.eTxtEmail);
//        btn_login = findViewById(R.id.); :v
        jk = findViewById(R.id.eTxtjk);
        tl = findViewById(R.id.eTxtTempatLahir);
        alamat = findViewById(R.id.eTxtAlamat);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_registrasi.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        link_regist = findViewById(R.id.btn_daftar);
        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN
        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaS = nama.getText().toString();
                String usernameS = username.getText().toString();
                String passwordS = pass.getText().toString();
                String emails = email.getText().toString();
                String jks = jk.getText().toString();
                String Tel = telp.getText().toString();
                    String Tl = tl.getText().toString();
                String alt = alamat.getText().toString();
                if (namaS.equals("") || usernameS.equals("") || passwordS.equals("") || emails.equals("") || jks.equals("")) {
                    Toast.makeText(getApplicationContext(), "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                    //memunculkan toast saat form masih ada yang kosong
                } else {
                    tambahdata(namaS, usernameS, passwordS, emails, jks, Tel, Tl, alt);
                }

            }
        });
    }

    public void regis() {

    }

    public void tambahdata(String namaS, String usernameS, String passwordS, String emails, String jks,String alt, String Tl, String Tel ) {
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.post(URL_REGISTER)
                .addBodyParameter("username", usernameS)
                .addBodyParameter("password", passwordS)
                .addBodyParameter("nama_lengkap", namaS)
                .addBodyParameter("email", emails)
                .addBodyParameter("jenis_kelamin", jks)
                .addBodyParameter("tempat_lahir", Tel)
                .addBodyParameter("alamat_lengkap",alt)
                .addBodyParameter("no_hp", Tl)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Handle Response
                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                        Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data berhasil ditambahkan
                        Intent intent = new Intent(activity_registrasi.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onError(ANError error) {
                        //Handle Error
                        Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                        Toast.makeText(getApplicationContext(), "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                });
    }
}