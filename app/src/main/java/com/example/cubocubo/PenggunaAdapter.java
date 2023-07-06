package com.example.cubocubo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class PenggunaAdapter extends RecyclerView.Adapter<PenggunaAdapter.ViewHolder> {
    Context context;
    List<M_tabel_pengguna> data;
    OnDeleteClickListener onDeleteClickListener;
    OnUpdateClickListener onUpdateClickListener;

    public PenggunaAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }
    public void add(M_tabel_pengguna item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }
    public void addAll(List<M_tabel_pengguna> items) {
        for (M_tabel_pengguna item : items) {
            add(item);
        }
    }
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }
    public M_tabel_pengguna getData(int position) {
        return data.get(position);
    }
    public void remove(int position) {
        if (position >= 0 && position < data.size()) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText etUsername;
        EditText etPassword;
        EditText etNohp;
        Button btnProfil;
        Button btnDaftar;
        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pengguna, parent,
                    false));
            initViews();
            btnProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUpdateClickListener.onUpdateClick(getAdapterPosition());
                }
            });
            btnDaftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClickListener.onDeleteClick(getAdapterPosition());
                }
            });
        }
        public void bind(M_tabel_pengguna item) {
            int nomer = getAdapterPosition() + 1;
            etUsername.setText(item.username);
            etPassword.setText(item.password);
            etNohp.setText(item.no_hp);
            btnProfil.setText(item.foto_profil);
        }
        public void initViews() {
            etUsername = (EditText) itemView.findViewById(R.id.etUsername);
            etPassword = (EditText) itemView.findViewById(R.id.etPassword);
            etNohp = (EditText) itemView.findViewById(R.id.etNohp);
            btnProfil = (Button) itemView.findViewById(R.id.btnProfil);
            btnDaftar = (Button) itemView.findViewById(R.id.btnDaftar);
        }
    }
}
