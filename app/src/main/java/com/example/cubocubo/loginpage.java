package com.example.cubocubo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loginpage extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    TextView tvDaftar;
    Button btnlogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        tvDaftar = (TextView)  findViewById(R.id. tvDaftar);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Bukabtnlogin = new Intent(getApplicationContext(),halaman2.class);
                startActivity(Bukabtnlogin);
            }

        });
        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BukatvDaftar = new Intent(getApplicationContext(),signuppage.class);
                startActivity(BukatvDaftar);
            }
        });
    }

}