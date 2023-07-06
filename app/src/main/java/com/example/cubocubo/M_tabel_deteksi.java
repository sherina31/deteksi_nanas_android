package com.example.cubocubo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class M_tabel_deteksi implements Parcelable {

    String id;
    String gambar_nanas;
    String kematangan_nanas;
    String ukuran_nanas;
    String id_pengguna;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGambar_nanas() {
        return gambar_nanas;
    }

    public void setGambar_nanas(String gambar_nanas) {
        this.gambar_nanas = gambar_nanas;
    }

    public String getKematangan_nanas() {
        return kematangan_nanas;
    }

    public void setKematangan_nanas(String kematangan_nanas) {
        this.kematangan_nanas = kematangan_nanas;
    }

    public String getUkuran_nanas() {
        return ukuran_nanas;
    }

    public void setUkuran_nanas(String ukuran_nanas) {
        this.ukuran_nanas = ukuran_nanas;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    protected M_tabel_deteksi(Parcel in) {
        id = in.readString();
        gambar_nanas = in.readString();
        kematangan_nanas = in.readString();
        ukuran_nanas = in.readString();
        id_pengguna = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(gambar_nanas);
        dest.writeString(kematangan_nanas);
        dest.writeString(ukuran_nanas);
        dest.writeString(id_pengguna);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<M_tabel_deteksi> CREATOR = new Creator<M_tabel_deteksi>() {
        @Override
        public M_tabel_deteksi createFromParcel(Parcel in) {
            return new M_tabel_deteksi(in);
        }

        @Override
        public M_tabel_deteksi[] newArray(int size) {
            return new M_tabel_deteksi[size];
        }
    };

}
