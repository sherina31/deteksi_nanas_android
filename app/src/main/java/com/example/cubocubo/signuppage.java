package com.example.cubocubo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
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
public class signuppage extends AppCompatActivity {
    private static final String TAG = signuppage.class.getSimpleName();
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
            showImagePickerDialog();
            }
        });

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

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // Gallery
                        openGallery();
                        break;
                    case 1: // Camera
                        openCamera();
                        break;
                }
            }
        });
        builder.show();
    }

    private void openGallery() {
        // Kode untuk membuka aplikasi galeri dan mengambil gambar dari galeri
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private void openCamera() {
        // Kode untuk membuka aplikasi kamera dan mengambil gambar dari kamera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                // Kode untuk menghandle gambar yang dipilih dari galeri
                if (data != null) {
                    Uri selectedImageUri = data.getData();
                    // Proses gambar yang dipilih dari galeri
                    // Contoh: menampilkan gambar pada ImageView
                    imageView.setImageURI(selectedImageUri);
                }
            } else if (requestCode == REQUEST_CAMERA) {
                // Kode untuk menghandle gambar yang diambil dari kamera
                if (data != null) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    // Proses gambar yang diambil dari kamera
                    // Contoh: menampilkan gambar pada ImageView
                    imageView.setImageBitmap(photo);
                }
            }
        }

}