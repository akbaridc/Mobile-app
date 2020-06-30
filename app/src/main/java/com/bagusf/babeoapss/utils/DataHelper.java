package com.bagusf.babeoapss.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "keranjang.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table keranjang(id_keranjang INTEGER primary key AUTOINCREMENT ," +
                " id_konsumen text null, id_produk text null, id_reseller text null," +
                " jumlah text null, total_harga text null, foto text null, namabarang text null,harga text null);";
        Log.d("DB", "cek :" +sql);
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
