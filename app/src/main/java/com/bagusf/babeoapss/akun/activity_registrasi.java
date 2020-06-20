package com.bagusf.babeoapss.akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bagusf.babeoapss.JavaNavigation;
import com.bagusf.babeoapss.R;
import com.bagusf.babeoapss.utils.VolleyHttp;
import com.bagusf.babeoapss.utils.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.bagusf.babeoapss.utils.network.URL_REGISTER;

public class activity_registrasi extends AppCompatActivity {
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String result;
    private EditText nama, pass, hidden, jk,telp,pass2 ,username,email;
    private Button btn_regis;
    private TextView link_regist;
    private ProgressBar progressBar;
    ProgressDialog progressDialog;
    private static final String TAG = "MainActivity"; //untuk melihat log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        nama = findViewById(R.id.eTxtNamaLengkap);
        username = findViewById(R.id.eTxtUsername);
        pass = findViewById(R.id.eTxtPassword);
//        hidden = findViewById(R.id.idhiden);
//        telp = findViewById(R.id.eTxtTelepon);
        email = findViewById(R.id.eTxtEmail);
//        btn_login = findViewById(R.id.); :v
        jk =findViewById(R.id.eTxtjk);
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
                if (namaS.equals("")||usernameS.equals("")||passwordS.equals("")||emails.equals("")||jks.equals("")){
                    Toast.makeText(getApplicationContext(),"Semua data harus diisi" , Toast.LENGTH_SHORT).show();
                    //memunculkan toast saat form masih ada yang kosong
                }else {
                    tambahdata(namaS , usernameS, passwordS,emails, jks);
                }

            }
        });
    }

    public void regis(){

    }
    public void tambahdata(String namaS , String usernameS, String passwordS,String emails, String jks){
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.post("http://192.168.1.12/API/api_babeo/auth/register")
                .addBodyParameter("nama_lengkap",namaS)
                .addBodyParameter("username", usernameS)
                .addBodyParameter("password", passwordS)
                .addBodyParameter("email", emails)
                .addBodyParameter("jenis_kelamin", jks)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Handle Response
                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                        Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data berhasil ditambahkan

                    }
                    @Override
                    public void onError(ANError error) {
                        //Handle Error
                        Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                        Toast.makeText(getApplicationContext(),"Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                });
    }
}