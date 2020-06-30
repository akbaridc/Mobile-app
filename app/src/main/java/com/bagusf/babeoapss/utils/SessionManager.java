package com.bagusf.babeoapss.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    SharedPreferences pref;

    Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "com.bagusf.sampah";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String FIRSTLOOK = "firstLook";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "firstLook";

    public static final String KEY_IDUSER = "id_user";

    public static final String KEY_NAMA = "nama_lengkap";

    public static final String ALAMAT = "alamat";

    public static final String EMAIL = "email";

    public static final String NOTLP = "notlp";



    public static final String STATUS = "status";

    public static final String JENISK = "jenis_kelamin";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String iduser, String nama ) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_IDUSER, iduser);

        editor.putString(KEY_NAMA, nama);

        editor.commit();
    }

    public boolean checkLogin() {
        // Check login status

        boolean stLogin = true;

        if (!this.isLoggedIn()) {

            stLogin = false;
        }

        return stLogin;

    }

    public void setLogin() {
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    public void setFirstlook() {

        editor.putBoolean(FIRSTLOOK, true);
        editor.commit();
    }

    public boolean checkFirstLook() {
        // Check login status

        boolean stLook = true;

        if (!this.isFirstLook()) {

            stLook = false;
        }

        return stLook;

    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        boolean look = false;
        if (this.checkFirstLook()) {
            look = true;
        }

        String iduser = this.getIduser();

        editor.clear();
        editor.commit();


        this.setIduser(iduser);

        if (look) {
            this.setFirstlook();
        }


    }





    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean isFirstLook() {
        return pref.getBoolean(FIRSTLOOK, false);
    }

    public String getIduser() {
        return pref.getString(KEY_IDUSER, null);
    }

    public String getNama() {
        return pref.getString(KEY_NAMA, null);
    }

    public String getAlamat() {
        return pref.getString(ALAMAT, null);
    }

    public String getEmail() {
        return pref.getString(EMAIL, null);
    }

    public String getNotlp() {
        return pref.getString(NOTLP, null);
    }

    public String getUsername() {
        return pref.getString(USERNAME, null);
    }

    public String getStatus() {
        return pref.getString(STATUS, null);
    }

    public String getJenisk() {
        return pref.getString(JENISK, null);
    }


    public void setAlamat(String val) {
        editor.putString(ALAMAT, val);
        editor.commit();
    }

    public void setEmail(String val) {
        editor.putString(EMAIL, val);
        editor.commit();
    }

    public void setNama(String nama) {
        editor.putString(KEY_NAMA, nama);
        editor.commit();
    }

    public void setIduser(String iduser) {
        editor.putString(KEY_IDUSER, iduser);
        editor.commit();
    }

    public void setNotlp(String value) {
        editor.putString(NOTLP, value);
        editor.commit();
    }

    public void setUsername(String value) {
        editor.putString(USERNAME, value);
        editor.commit();
    }

    public void setStatus(String level) {
        editor.putString(STATUS, level);
        editor.commit();
    }

    public void setJenisk(String namatoko) {
        editor.putString(JENISK, namatoko);
        editor.commit();
    }
    public void setPassword(String password){
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public static final String NAMALOKASI = "nama_lokasi";
    public static final String LANGITUDE = "langitude";
    public static final String LATITUDE = "latitude";
    public static final String KETERANGAN = "keterangan";

    public String getNamalokasi() {
        return pref.getString(NAMALOKASI, null);
    }
    public String getLangitude() {
        return pref.getString(LANGITUDE, null);
    }
    public String getLatitude() {
        return pref.getString(LATITUDE, null);
    }
    public String getKeterangan() {
        return pref.getString(KETERANGAN, null);
    }
    public  String getPassword(){ return pref.getString(PASSWORD, null);}

    public void setNamalokasi(String value) {
        editor.putString(NAMALOKASI, value);
        editor.commit();
    }

    public void setLangitude(String value) {
        editor.putString(LANGITUDE, value);
        editor.commit();
    }

    public void setLatitude(String value) {
        editor.putString(LATITUDE, value);
        editor.commit();
    }

    public void setKeterangan(String value) {
        editor.putString(KETERANGAN, value);
        editor.commit();
    }


}