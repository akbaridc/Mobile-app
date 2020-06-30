package com.bagusf.babeoapss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bagusf.babeoapss.R;
import com.bagusf.babeoapss.model.model_riwayat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class adapter_riwayat extends RecyclerView.Adapter<adapter_riwayat.holder> {
    Context context;
    ArrayList<model_riwayat> arrayList;

    public adapter_riwayat(Context context, ArrayList<model_riwayat> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_riwayat, parent, false);
        return new holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.txtKode.setText(arrayList.get(position).getKode());
        holder.txtStatus.setText(arrayList.get(position).getStatus());
        holder.txtTanggal.setText(arrayList.get(position).getTgl());
        if (arrayList.get(position).getStatus().equals("0")){
            holder.txtStatus.setText("Pending");
            holder.productImage.setImageResource(R.drawable.ic_pending);
        }else if(arrayList.get(position).getStatus().equals("1")){
            holder.txtStatus.setText("Konfirmasi");
            holder.productImage.setImageResource(R.drawable.ic_konfirmasi);
        }else if(arrayList.get(position).getStatus().equals("2")){
        holder.txtStatus.setText("Sudah Dikonfirmasi");
        holder.productImage.setImageResource(R.drawable.ic_konfirmasi);
    }

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.txt_kode)
        TextView txtKode;
        @BindView(R.id.txt_tanggal)
        TextView txtTanggal;
        @BindView(R.id.txt_status)
        TextView txtStatus;
        public holder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
