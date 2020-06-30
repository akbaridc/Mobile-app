package com.bagusf.babeoapss.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bagusf.babeoapss.R;
import com.bagusf.babeoapss.model.model_keranjang;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class adapter_keranjang extends RecyclerView.Adapter<adapter_keranjang.holder> {
    Context context;
    ArrayList<model_keranjang> arrayList;



    public adapter_keranjang(Context context, ArrayList<model_keranjang> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_keranjang, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Intent intent = new Intent("intent");
        Glide.with(context).load("http://babeo.mif-project.com/asset/foto_produk/" + arrayList.get(position).getFoto())
                .thumbnail(0.2f)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.productImage);
        holder.btnkurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(arrayList.get(position).getJumlah());

                int hrg = Integer.parseInt(arrayList.get(position).getHarga());
                int ttl = Integer.parseInt(arrayList.get(position).getTotalharga());
                int jml = a - 1;
                int ttlhrg = ttl - hrg;
                arrayList.get(position).setJumlah(String.valueOf(jml));
                arrayList.get(position).setTotalharga(String.valueOf(ttlhrg));
                Log.i("kurang", "onClick: " + jml);
                notifyItemChanged(position);

                intent.putExtra("result", "1");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });
        holder.btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = Integer.parseInt(arrayList.get(position).getJumlah());
                int hrg = Integer.parseInt(arrayList.get(position).getHarga());
                int ttl = Integer.parseInt(arrayList.get(position).getTotalharga());
                int jml = b + 1;
                int ttlhrg = ttl + hrg;
                arrayList.get(position).setJumlah(String.valueOf(jml));
                arrayList.get(position).setTotalharga(String.valueOf(ttlhrg));
                Log.i("tambah", "onClick: " + jml);
                notifyItemChanged(position);

                intent.putExtra("result", "1");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(position);
                notifyItemRemoved(position);

                intent.putExtra("result", "1");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

        holder.edittext.setText(arrayList.get(position).getJumlah());
        holder.txtProductName.setText(arrayList.get(position).getNama());
        holder.txtPrice.setText(arrayList.get(position).getTotalharga());
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.txt_product_name)
        TextView txtProductName;

        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.btnkurang)
        Button btnkurang;
        @BindView(R.id.edittext)
        EditText edittext;
        @BindView(R.id.btntambah)
        Button btntambah;
        @BindView(R.id.btnRemove)
        Button btnRemove;

        public holder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
