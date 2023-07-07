package com.example.cubocubo;

public class Server {
    public static String BASE_URL = "http://192.168.1.53/api_database_klasifikasinanas/";
//    public static final String API_CREATE = "data/add";
//    public static final String API_READ = "data/read";
//    public static final String API_UPDATE = "data/edit/";
//    public static final String API_DELETE = "data/delete/";
//    public static final String API_UPLOAD = "data/uploadimage/";
    // server untuk tabel_pengguna
    public static final String API_CREATE_PENGGUNA = "data/registrasi.php";
    public static final String API_READ_PENGGUNA = "data/lihat_tabel_pengguna.php";
    public static final String API_UPDATE_PENGGUNA = "data/edit_tabel_pengguna.php";
    public static final String API_DELETE_PENGGUNA = "data/hapus_tabel_pengguna.php";
//    public static final String API_UPLOAD_PENGGUNA = "data/registrasi.php";

    //server untuk tabel_deteksi
    public static final String API_CREATE_DETEKSI = "data/tambah_tabel_deteksi.php";
    public static final String API_READ_DETEKSI = "data/lihat_tabel_deteksi.php";
    public static final String API_UPDATE_DETEKSI = "data/edit_tabel_deteksi.php";
    public static final String API_DELETE_DETEKSI = "data/hapus_tabel_deteksi.php";

    //public static final String API_UPLOAD_DETEKSI = "data/uploadimage.php";
}
