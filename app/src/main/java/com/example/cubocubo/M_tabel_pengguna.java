package com.example.cubocubo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class M_tabel_pengguna implements Parcelable {
    String id;
    String username;
    String password;
    String no_hp;
    String foto_profil;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getFoto_profil() {
        return foto_profil;
    }

    public void setFoto_profil(String foto_profil) {
        this.foto_profil = foto_profil;
    }

    protected M_tabel_pengguna(Parcel in) {
        id = in.readString();
        username = in.readString();
        password = in.readString();
        no_hp = in.readString();
        foto_profil = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(no_hp);
        dest.writeString(foto_profil);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<M_tabel_pengguna> CREATOR = new Creator<M_tabel_pengguna>() {
        @Override
        public M_tabel_pengguna createFromParcel(Parcel in) {
            return new M_tabel_pengguna(in);
        }

        @Override
        public M_tabel_pengguna[] newArray(int size) {
            return new M_tabel_pengguna[size];
        }
    };

}