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
import com.bagusf.babeoapss.model.model_keranjang;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

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
        Glide.with(context).load("http://192.168.1.12/API/api_babeo/asset/foto_produk/"+arrayList.get(position).getFoto())
                .thumbnail(0.2f)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.productImage);

        holder.txtProductName.setText(arrayList.get(position).getNama());
        holder.txtPrice.setText(arrayList.get(position).getHarga());
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
        @BindView(R.id.txt_colour)
        TextView txtColour;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.txt_quantity_amount)
        ElegantNumberButton txtQuantityAmount;
        public holder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
