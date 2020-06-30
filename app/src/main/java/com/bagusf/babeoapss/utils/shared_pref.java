package com.bagusf.babeoapss.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class shared_pref {
    public static final String SP_status = "login";

    public static final String SP_nama= "spNama";
    public static final String SP_email = "spEmail";
    public static final String SP_foto= "spFoto";


    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public shared_pref(Context context){
        sp = context.getSharedPreferences(SP_status, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_nama, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_email, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
