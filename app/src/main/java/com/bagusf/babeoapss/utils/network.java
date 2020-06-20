package com.bagusf.babeoapss.utils;

public class network {
    public static final String BASE_URL = "http://192.168.1.12/";
    public static final String httpTag = "KillHttp";
    public static final String BASE_API = "API/api_babeo/";
    //    public static final String BASE_API = "api_kegiatan_dakwah_fkl/";

    //    BASED
//    private static final String BASE_INDEX = BASE_URL + "api_majlis_taklim_mobile/index.php/";
    private static final String BASE_INDEX = BASE_URL + BASE_API;
    //    LOGIN controler/fungsi
    public static final String URL_LOGIN = BASE_INDEX + "auth/login";// tulisane no connection?
    public static final String URL_LOGOUT = BASE_INDEX + "auth/login";
    public static final String URL_REGISTER = BASE_INDEX + "auth/register";
    public static final String URL_GETPRODUK= BASE_INDEX + "auth/data";
    public static final String URL_GET_KERANJANG= BASE_INDEX + "auth/read_keranjang";
}
