package com.bagusf.babeoapss.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bagusf.babeoapss.utils.network.URL_GETPRODUK;
import static com.bagusf.babeoapss.utils.network.URL_GET_KERANJANG;

public class fragment_keranjang extends Fragment {
    ArrayList<model_keranjang> arrayList = new ArrayList<>();
//    @BindView(R.id.placeOrderBtn)
//    Button placeOrderBtn;
    @BindView(R.id.recyclekeranjang)
    RecyclerView recyclekeranjang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_keranjang, container, false);
        ButterKnife.bind(this, view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclekeranjang.setLayoutManager(manager);
        recyclekeranjang.setHasFixedSize(true);
        init();
        return view;
    }

    public void init() {
        arrayList.clear();

        AndroidNetworking.post(URL_GET_KERANJANG)
                .addBodyParameter("id_konsumen","1")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.i("respon", "onResponse: " + jsonObject.getString("jumlah"));
                                model_keranjang m_keranjang = new model_keranjang();
                                m_keranjang.setTotalharga(jsonObject.getString("total_harga"));
                                m_keranjang.setJumlah(jsonObject.getString("jumlah"));
                                m_keranjang.setFoto(jsonObject.getString("gambar"));
                                m_keranjang.setNama(jsonObject.getString("nama_produk"));
                                m_keranjang.setHarga(jsonObject.getString("harga"));
                                m_keranjang.setId(jsonObject.getString("id_produk"));
                                arrayList.add(m_keranjang);
                            }
                            adapter_keranjang a_keranjang = new adapter_keranjang(getActivity(), arrayList);
                            recyclekeranjang.setAdapter(a_keranjang);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getActivity(), "kesalahan", Toast.LENGTH_LONG).show();
                        Log.e("error", "onError: " + anError.getErrorDetail());
                    }
                });


    }
}

