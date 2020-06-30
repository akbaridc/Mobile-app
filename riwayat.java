package com.bagusf.babeoapss;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bagusf.babeoapss.adapter.adapter_keranjang;
import com.bagusf.babeoapss.adapter.adapter_riwayat;
import com.bagusf.babeoapss.model.model_keranjang;
import com.bagusf.babeoapss.model.model_riwayat;
import com.bagusf.babeoapss.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bagusf.babeoapss.utils.network.URL_GET_KERANJANG;
import static com.bagusf.babeoapss.utils.network.URL_RIWAYAT;

public class riwayat extends AppCompatActivity {


    @BindView(R.id.recycleriwayat)
    RecyclerView recycleriwayat;
    SessionManager sessionManager;
    ArrayList<model_riwayat> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        ButterKnife.bind(this);

        GridLayoutManager manager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recycleriwayat.setLayoutManager(manager);
        recycleriwayat.setHasFixedSize(true);
        AndroidNetworking.initialize(this);
        sessionManager = new SessionManager(this);
        readriwayat();
    }
    public void readriwayat(){
        arrayList.clear();
        AndroidNetworking.post(URL_RIWAYAT)
                .addBodyParameter("id_konsumen", sessionManager.getIduser())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                model_riwayat m_riwayat = new model_riwayat();
                                m_riwayat.setId(jsonObject.getString("id_penjualan"));
                                m_riwayat.setKode(jsonObject.getString("kode_transaksi"));
                                m_riwayat.setStatus(jsonObject.getString("proses"));
                                m_riwayat.setTgl(jsonObject.getString("waktu_transaksi"));
                                arrayList.add(m_riwayat);
                            }
//                          Log.i("size : ", String.valueOf(arrayList.size()));
                            adapter_riwayat a_riwayat = new adapter_riwayat(riwayat.this, arrayList);
                            recycleriwayat.setAdapter(a_riwayat);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(riwayat.this, "kesalahan", Toast.LENGTH_LONG).show();
                        Log.e("error", "onError: " + anError.getErrorDetail());
                    }
                });

    }
}
