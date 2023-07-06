package com.example.cubocubo;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;



public class DeteksiAdapter extends RecyclerView.Adapter<DeteksiAdapter.ViewHolder> {
    Context context;
    List<M_tabel_deteksi> data;
    OnDeleteClickListener onDeleteClickListener;
    OnUpdateClickListener onUpdateClickListener;

    public DeteksiAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }
    public void add(M_tabel_deteksi item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }
    public void addAll(List<M_tabel_deteksi> items) {
        for (M_tabel_deteksi item : items) {
            add(item);
        }
    }
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }
    public M_tabel_deteksi getData(int position) {
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
        TextView tvKematangan;
        TextView tvUkuran;
        ImageView indikator;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_deteksi, parent,
                    false));
            initViews();
//            btnEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onUpdateClickListener.onUpdateClick(getAdapterPosition());
//                }
//            });
//            btnRemove.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onDeleteClickListener.onDeleteClick(getAdapterPosition());
//                }
//            });
        }
        public void bind(M_tabel_deteksi item) {
            int nomer = getAdapterPosition() + 1;
            tvKematangan.setText(item.getKematangan_nanas());
            tvUkuran.setText(item.getUkuran_nanas());
            if (item.getUkuran_nanas().equals("besar")) {
                indikator.setImageResource(R.drawable.indikatormerah);
            } else if (item.getUkuran_nanas().equals("sedang")) {
                indikator.setImageResource(R.drawable.indikatorbiru);
            } else if (item.getUkuran_nanas().equals("kecil")) {
                indikator.setImageResource(R.drawable.indikatorhijau);
            }
        }

        public void initViews() {
            tvKematangan = (TextView) itemView.findViewById(R.id.tvKematangan);
            tvUkuran = (TextView) itemView.findViewById(R.id.tvUkuran);
            indikator = (ImageView) itemView.findViewById(R.id.indikator);

        }
    }

}