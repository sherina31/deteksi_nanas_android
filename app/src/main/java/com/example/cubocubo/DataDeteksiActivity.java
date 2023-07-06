package com.example.cubocubo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDeteksiActivity extends AppCompatActivity implements OnDeleteClickListener,
        OnUpdateClickListener {
    private static final String TAG = DataDeteksiActivity.class.getSimpleName();
    private RecyclerView rvData;
    private DeteksiAdapter adapter;
    private DataService service;
    public static void newInstance(Context context) {
        Intent intent = new Intent(context, DataDeteksiActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_deteksi);
        initViews();
        // Initialization adapter
        adapter = new DeteksiAdapter(this);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        service = ServiceGenerator.createBaseService(this, DataService.class);
        rvData.setAdapter(adapter);
        loadData();
    }
    private void loadData() {
        Call<BaseResponse<List<M_tabel_deteksi>>> call = service.apiReadDeteksi();
        call.enqueue(new Callback<BaseResponse<List<M_tabel_deteksi>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<M_tabel_deteksi>>> call,
                                   Response<BaseResponse<List<M_tabel_deteksi>>> response) {
                if(response.code() == 200) {
                    adapter.addAll(response.body().getData());
                    initListener();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<List<M_tabel_deteksi>>> call, Throwable t) {
                Log.e(TAG+".error", t.toString());
            }
        });
    }
    private void initListener() {
        adapter.setOnDeleteClickListener(this);
        adapter.setOnUpdateClickListener(this);
    }
    private void initViews() {
        rvData = (RecyclerView) findViewById(R.id.rv_data);
    }
    private void doDelete(final int position, String id) {
        Call<BaseResponse> call = service.apiDeleteDeteksi(id);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code() == 200)
                    adapter.remove(position);
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e(TAG+".errorDelete", t.toString());
            }
        });
    }
    @Override
    public void onDeleteClick(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Yakin Ingin Menghapusnya?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                doDelete(position, adapter.getData(position).getId());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    @Override
    public void onUpdateClick(int position) {
        M_tabel_deteksi data = adapter.getData(position);
    }

}