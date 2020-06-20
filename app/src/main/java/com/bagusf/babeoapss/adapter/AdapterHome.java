package com.bagusf.babeoapss.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bagusf.babeoapss.activity_detail_produk;
import com.bagusf.babeoapss.model.ModelHome;
import com.bagusf.babeoapss.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.holder> {


    private ArrayList<ModelHome> arrayList;
    private Context context;

    public AdapterHome(ArrayList<ModelHome> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Glide.with(context).load("http://192.168.1.12/API/api_babeo/asset/foto_produk/"+arrayList.get(position).getFoto())
                .thumbnail(0.2f)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgItemCard);
        holder.txtNamaItemCard.setText(arrayList.get(position).getNama());
        holder.txtHrgItemCard.setText(arrayList.get(position).getHarga());

        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, activity_detail_produk.class);
                intent.putExtra("nama_produk", arrayList.get(position).getNama());
                intent.putExtra("foto", arrayList.get(position).getFoto());
                intent.putExtra("harga", arrayList.get(position).getHarga());
                intent.putExtra("id_produk", arrayList.get(position).getId());
                context.startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item_card)
        ImageView imgItemCard;
        @BindView(R.id.txt_nama_item_card)
        TextView txtNamaItemCard;
        @BindView(R.id.txt_hrg_item_card)
        TextView txtHrgItemCard;
        @BindView(R.id.card_item)
        CardView cardItem;
        public holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
