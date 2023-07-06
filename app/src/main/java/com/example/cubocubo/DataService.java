package com.example.cubocubo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface DataService {

    // kodingan data service tabel_pengguna
    @FormUrlEncoded
    @POST(Server.API_CREATE_PENGGUNA)
    Call<BaseResponse> apiCreatePengguna(
            @Field("username") String username,
            @Field("password") String password,
            @Field("no_hp") String no_hp
//            @Field("foto_profil") String foto_profil
    );

    @GET(Server.API_READ_PENGGUNA)
    Call<BaseResponse<List<M_tabel_pengguna>>> apiReadPengguna();
    @FormUrlEncoded

    @POST(Server.API_UPDATE_PENGGUNA)
    Call<BaseResponse> apiUpdatePengguna(
            @Field("id") String id,
            @Field("username") String username,
            @Field("password") String password,
            @Field("no_hp") String no_hp,
            @Field("foto_profil") String foto_profil
    );
    @FormUrlEncoded
    @POST(Server.API_DELETE_PENGGUNA)
    Call<BaseResponse> apiDeletePengguna(@Field("id") String id);

    // kodingan data service tabel_deteksi
    @FormUrlEncoded
    @POST(Server.API_CREATE_DETEKSI)
    Call<BaseResponse> apiCreateDeteksi(
            @Field("gambar_nanas") String gambar_nanas,
            @Field("kematangan_nanas") String kematangan_nanas,
            @Field("ukuran_nanas") String ukuran_nanas,
            @Field("id_pengguna") String id_pengguna
    );

    @GET(Server.API_READ_DETEKSI)
    Call<BaseResponse<List<M_tabel_deteksi>>> apiReadDeteksi();
    @FormUrlEncoded

    @POST(Server.API_UPDATE_DETEKSI)
    Call<BaseResponse> apiUpdateDeteksi(
            @Field("id") String id,
            @Field("id_pengguna") String id_pengguna,
            @Field("gambar_nanas") String gambar_nanas,
            @Field("kematangan_nanas") String kematangan_nanas,
            @Field("ukuran_nanas") String ukuran_nanas
    );

    @FormUrlEncoded
    @POST(Server.API_DELETE_DETEKSI)
    Call<BaseResponse> apiDeleteDeteksi(@Field("id") String id);

}
