package com.bagusf.babeoapss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bagusf.babeoapss.utils.DataHelper;
import com.bagusf.babeoapss.utils.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class activity_detail_produk extends AppCompatActivity {
    String id, harga, nama, foto, id_reseller;
    @BindView(R.id.itemproductImageViewbos)
    ImageView itemproductImageViewbos;
    @BindView(R.id.itemproductTextViewDisc)
    TextView itemproductTextViewDisc;
    @BindView(R.id.itemproductTextViewNameboss)
    TextView itemproductTextViewNameboss;
    @BindView(R.id.itemproductTextViewPriceboss)
    TextView itemproductTextViewPriceboss;
    @BindView(R.id.itemproductRatingBar1)
    RatingBar itemproductRatingBar1;
    @BindView(R.id.keranjang)
    Button keranjang;
    SessionManager sessionManager;
    DataHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        dbHelper = new DataHelper(activity_detail_produk.this);
        id = intent.getStringExtra("id_produk");
        nama = intent.getStringExtra("nama_produk");
        harga = intent.getStringExtra("harga");
        foto = intent.getStringExtra("foto");
        id_reseller = intent.getStringExtra("id_penjual");
        sessionManager = new SessionManager(this);
        itemproductTextViewNameboss.setText(nama);
        itemproductTextViewPriceboss.setText(harga);
        Glide.with(this).load("http://babeo.mif-project.com//asset/foto_produk/" + foto)
                .thumbnail(0.2f)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemproductImageViewbos);
        keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into keranjang(id_konsumen, id_produk, jumlah, id_reseller, total_harga, foto, harga,namabarang) values('" +
                        sessionManager.getIduser() + "','" +
                        id + "','" +
                        "1" + "','" +
                        id_reseller + "','" +
                        harga + "','" +
                        foto + "','" +
                        harga + "','" +
                        nama + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                finish();

               // addkeranjang();
            }
        });
    }
    public void addkeranjang(){
        AndroidNetworking.post("http://192.168.1.12/API/api_babeo/auth/insert_keranjang")
                .addBodyParameter("id_konsumen",sessionManager.getIduser())
                .addBodyParameter("id_produk", id)
                .addBodyParameter("jumlah", "1")
                .addBodyParameter("id_reseller",id_reseller)
                .addBodyParameter("total_harga", harga)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Handle Response
//                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                        Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data berhasil ditambahkan
finish();

                    }
                    @Override
                    public void onError(ANError error) {
                        //Handle Error
                        Log.d("error", "onError: Failed" + error.getErrorBody()); //untuk log pada onerror
                        Toast.makeText(getApplicationContext(),"Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                });

    }
}
