package com.bagusf.babeoapss.akun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bagusf.babeoapss.JavaNavigation;
import com.bagusf.babeoapss.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText username, pass;
    private Button btn_login;
    private TextView link_regist;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AndroidNetworking.initialize(this);
        username = findViewById(R.id.eTxtUsername);
        pass = findViewById(R.id.eTxtPassword);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksiButton();
            }
        });
        link_regist = findViewById(R.id.daftar);
        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, activity_registrasi.class);
                startActivity(intent);
            }
        });


    }
    public void aksiButton(){
       AndroidNetworking.post("http://192.168.102.106/API/api_babeo/auth/login")
               .addBodyParameter("username", username.getText().toString())
               .addBodyParameter("password", pass.getText().toString())
               .setTag(this)
               .setPriority(Priority.HIGH)
               .build()
               .getAsJSONObject(new JSONObjectRequestListener() {
                   @Override
                   public void onResponse(JSONObject response) {
                       try{
                           String status = response.getString("result");
                           String message = response.getString("message");
                           if(status.equals("true")){

                               Log.d("username",response.getString("username"));
                               Log.d("f",response.getString("username"));
                               showMessage(message);
                               Intent intent = new Intent(LoginActivity.this, JavaNavigation.class);
                               startActivity(intent);
                           }else {
                               showMessage(message);

                           }
                   }catch (JSONException e){
                           e.printStackTrace();

                       }
                   }

                   @Override
                   public void onError(ANError anError) {

                   }
               });

    }

    private void showMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
