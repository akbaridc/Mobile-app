package com.bagusf.babeoapss.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bagusf.babeoapss.R;
import com.bagusf.babeoapss.adapter.adapter_keranjang;
import com.bagusf.babeoapss.model.model_keranjang;
import com.bagusf.babeoapss.utils.DataHelper;
import com.bagusf.babeoapss.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bagusf.babeoapss.utils.network.URL_PENJUALAN;
import static com.bagusf.babeoapss.utils.network.URL_PENJUALAN_DETAIL;

public class fragment_keranjang extends Fragment {
    ArrayList<model_keranjang> arrayList = new ArrayList<>();
    //    @BindView(R.id.placeOrderBtn)
//    Button placeOrderBtn;
    @BindView(R.id.recyclekeranjang)
    RecyclerView recyclekeranjang;
    SessionManager sessionManager;
    @BindView(R.id.textdetil)
    LinearLayout textdetil;
    @BindView(R.id.chekout)
    Button chekout;
    protected Cursor cursor;
    DataHelper dbHelper;
    int totalharga = 0;

    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.total)
    TextView total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_keranjang, container, false);
        ButterKnife.bind(this, view);
        dbHelper = new DataHelper(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclekeranjang.setLayoutManager(manager);
        recyclekeranjang.setHasFixedSize(true);

        sessionManager = new SessionManager(getActivity());
        chekout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addpenjualan();
            }
        });
        Log.i("iduser", "onCreateView: " + sessionManager.getIduser());
        init();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("intent"));
        return view;
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("result");
            if (message.equals("1")){
                settotal();
            }
        }
    };

    @Override
    public void onStop() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onStop();
    }

    public void settotal(){
        if (arrayList.isEmpty()){
            total.setText("" + 0);
        }else{

            for (int i = 0; i < arrayList.size(); i++) {
                totalharga += Integer.parseInt(arrayList.get(i).getTotalharga());
                total.setText("" + totalharga);
                Log.i("totalharga", "init: " + totalharga);
            }
        }
    }

    public void init() {

        arrayList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM keranjang", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            model_keranjang m_keranjang = new model_keranjang();
            m_keranjang.setTotalharga(cursor.getString(5));
            m_keranjang.setJumlah(cursor.getString(4));
            m_keranjang.setFoto(cursor.getString(6));
            m_keranjang.setNama(cursor.getString(7));
            m_keranjang.setHarga(cursor.getString(8));
            m_keranjang.setId2(cursor.getString(3));
            m_keranjang.setId(cursor.getString(2));
            arrayList.add(m_keranjang);
//            total.setText(totalharga);
            Log.i("CEK ID", "ID"+ cursor.getString(3));
        }
        Log.i("size : ", String.valueOf(arrayList.size()));

        adapter_keranjang a_keranjang = new adapter_keranjang(getActivity(), arrayList);
        recyclekeranjang.setAdapter(a_keranjang);
        settotal();
//        AndroidNetworking.post(URL_GET_KERANJANG)
//                .addBodyParameter("id_konsumen", sessionManager.getIduser())
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("data");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                Log.i("respon", "onResponse: " + jsonObject.getString("jumlah"));
//                                model_keranjang m_keranjang = new model_keranjang();
//                                m_keranjang.setTotalharga(jsonObject.getString("total_harga"));
//                                m_keranjang.setJumlah(jsonObject.getString("jumlah"));
//                                m_keranjang.setFoto(jsonObject.getString("gambar"));
//                                m_keranjang.setNama(jsonObject.getString("nama_produk"));
//                                m_keranjang.setHarga(jsonObject.getString("harga"));
//                                m_keranjang.setId(jsonObject.getString("id_produk"));
//                                arrayList.add(m_keranjang);
//                            }
//                            Log.i("size : ", String.valueOf(arrayList.size()));
//                            adapter_keranjang a_keranjang = new adapter_keranjang(getActivity(), arrayList);
//                            recyclekeranjang.setAdapter(a_keranjang);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Toast.makeText(getActivity(), "kesalahan", Toast.LENGTH_LONG).show();
//                        Log.e("error", "onError: " + anError.getErrorDetail());
//                    }
//                });
//

    }

    public void addpenjualan() {
        AndroidNetworking.post(URL_PENJUALAN)
                .addBodyParameter("id_pembeli", sessionManager.getIduser())
                .addBodyParameter("id_penjual", cursor.getString(3))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("result").equals("true")) {
                                addpenjualandetail(response.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Handle Response
//                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
//                        Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data berhasil ditambahkan

                    }

                    @Override
                    public void onError(ANError error) {
                        //Handle Error
                        Log.d("error", "onError: Failed" + error.getErrorBody()); //untuk log pada onerror
                        Toast.makeText(getActivity(), "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                });

    }

    public void addpenjualandetail(String id_penjualan) {
        for (int i = 0; i < arrayList.size(); i++) {

            int finalI = i;
            AndroidNetworking.post(URL_PENJUALAN_DETAIL)
//                    .addBodyParameter("id_pembeli",sessionManager.getIduser())
                    .addBodyParameter("id_penjualan", id_penjualan)
                    .addBodyParameter("id_produk", arrayList.get(i).getId())
                    .addBodyParameter("jumlah", arrayList.get(i).getJumlah())
                    .addBodyParameter("harga_jual", arrayList.get(i).getHarga())
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Handle Response
//                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
//                        Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                            //memunculkan Toast saat data berhasil ditambahkan
                            try {
                                if (finalI == arrayList.size() - 1) {
                                    if (response.getString("result").equals("true")) {
                                        Toast.makeText(getActivity(), "chekout berhasil", Toast.LENGTH_SHORT).show();
                                        hpus();
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError error) {
                            //Handle Error
                            Log.d("error", "onError: Failed" + error.getErrorBody()); //untuk log pada onerror
                            Toast.makeText(getActivity(), "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                            //memunculkan Toast saat data gagal ditambahkan
                        }
                    });

        }
    }

    public void hpus() {

    }
}

