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
    List<M_tabel_deteksi> deteksiList;
    OnDeleteClickListener onDeleteClickListener;
    OnUpdateClickListener onUpdateClickListener;

    public DeteksiAdapter(Context context) {
        this.context = context;
        deteksiList = new ArrayList<>();
    }
    public void addAll(List<M_tabel_deteksi> deteksiList ) {
        this.deteksiList.clear();
        this.deteksiList.addAll(deteksiList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_deteksi,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       M_tabel_deteksi deteksi = deteksiList.get(position);
       holder.tvKematangan.setText(deteksi.getKematangan_nanas());
       holder.tvUkuran.setText(deteksi.getUkuran_nanas());
        if (deteksi.getUkuran_nanas().equals("besar")) {
            holder.indikator.setImageResource(R.drawable.indikatormerah);
        } else if (deteksi.getUkuran_nanas().equals("sedang")) {
            holder.indikator.setImageResource(R.drawable.indikatorbiru);
        } else if (deteksi.getUkuran_nanas().equals("kecil")) {
            holder.indikator.setImageResource(R.drawable.indikatorhijau);
        }
    }
    @Override
    public int getItemCount() {

        return deteksiList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKematangan;
        TextView tvUkuran;
        ImageView indikator;

        public ViewHolder(View itemView){
            super(itemView);
            tvKematangan = itemView.findViewById(R.id.tvKematangan);
            tvUkuran = itemView.findViewById(R.id.tvUkuran);
            indikator = itemView.findViewById(R.id.indikator);
        }

    }

}