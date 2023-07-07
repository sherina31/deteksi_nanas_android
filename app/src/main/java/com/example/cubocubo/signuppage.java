package com.example.cubocubo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.strictmode.FileUriExposedViolation;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.widget.ImageView;
import android.view.View;
import com.squareup.picasso.Picasso;
import java.io.File;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.text.TextUtils.isEmpty;

import org.w3c.dom.Text;

public class signuppage extends AppCompatActivity {
    private static final String TAG = signuppage.class.getSimpleName();
    private static final int MY_RESULT_CODE_PERMISSION = 1000;
    private static final int MY_RESULT_CODE_FILECHOOSEER = 2000;
    String lokasiFile;
    private DataService service;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etNohp;
    private Button btnProfil, btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        initViews();
        initListener();
        service = ServiceGenerator.createBaseService(this, DataService.class);
        btnDaftar = findViewById(R.id.btnDaftar);
    }

    private void initListener() {
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String no_hp = etNohp.getText().toString();
                if(isEmpty(username))
                    etUsername.setError("Tolong Di Isi");
                else if (isEmpty(password))
                    etPassword.setError("Tolong Di Isi");
                else if (isEmpty(no_hp))
                    etNohp.setError("Tolong Di Isi");
                else
                    addData(username,password,no_hp);
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) ==PackageManager.PERMISSION_GRANTED){
                    pilihFile();
            }else {
                deteksiPermissionandroid();
            }
        }
    });

    }

    private void pilihFile() {
        String[] tipeFile =
                {"image/jpg","image/jpeg,'image/png"};
        Intent intentPilihFile = new Intent(Intent.ACTION_GET_CONTENT);
        intentPilihFile.addCategory(Intent.CATEGORY_OPENABLE);
        intentPilihFile.setType("*/*");
        intentPilihFile.putExtra(Intent.EXTRA_MIME_TYPES, tipeFile);
        startIntentSenderForResult(Intent.createChooser(intentPilihFile,"Pilih foto anda" ));

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intext
        switch (requestCode) {
            case MY_RESULT_CODE_FILECHOOSEER:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        Uri fileUri = data.getData();
                        String filePath = null;
                        try {
                            filePath = FileUtils.getPath(getApplicationContext());
                        } catch (Exception e) {
                            Toast.makeText((getApplicationContext(), ))
                        }
                        this.lokasiFile = filePath;
                        //proses munculi gambar
                        Picasso.get().load(new File(lokasiFile)).into();
                        Toast.makeText(getApplicationContext(), );
                        txtLokasi.setText.(lokasiFile);
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void addData(String username, String password, String no_hp) {
        Call<BaseResponse> call = service.apiCreatePengguna(username,password,no_hp);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code() == 200) {
                    Toast.makeText(signuppage.this, "Berhasil Bestie", Toast.LENGTH_SHORT).show();
                    etUsername.setText("");
                    etPassword.setText("");
                    etNohp.setText("");
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e(TAG + ".error", t.toString());
                Toast.makeText(signuppage.this, "Gagal Bestie", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initViews() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etNohp = (EditText) findViewById(R.id.etNohp);
        btnProfil = (Button) findViewById(R.id.btnProfil);
        btnDaftar = (Button) findViewById(R.id.btnDaftar);
    }

}