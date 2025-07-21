package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Không được để trống tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!username.matches("^[A-Za-z]+$")) {
                    Toast.makeText(RegisterActivity.this, "Tên đăng nhập chỉ được chứa chữ cái (a-z, A-Z)", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!password.matches("^[0-9]+$")) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu chỉ được chứa số (0-9)", Toast.LENGTH_SHORT).show();
                    return;
                }


                Toast.makeText(RegisterActivity.this, "Register successful!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}