package com.bagusf.babeoapss;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bagusf.babeoapss.adapter.AdapterHome;
import com.bagusf.babeoapss.model.ModelHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bagusf.babeoapss.utils.network.URL_GETPRODUK;

public class Fragment_home extends Fragment {
    ArrayList<ModelHome> arrayList = new ArrayList<>();
    @BindView(R.id.cardListView1)
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home, container, false);
        ButterKnife.bind(this,view);
        AndroidNetworking.initialize(getActivity());

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        init();


        return view;

    }



    public void init() {
        arrayList.clear();
        AndroidNetworking.post(URL_GETPRODUK)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelHome modelHome= new ModelHome();
                                modelHome.setFoto(jsonObject.getString("gambar"));
                                modelHome.setNama(jsonObject.getString("nama_produk"));
                                modelHome.setId_reseller(jsonObject.getString("id_reseller"));
                                modelHome.setHarga(jsonObject.getString("harga"));
                                modelHome.setId(jsonObject.getString("id_produk"));
                                arrayList.add(modelHome);
                            }
                            AdapterHome adapterHome = new AdapterHome(arrayList, getActivity());
                            recyclerView.setAdapter(adapterHome);
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
