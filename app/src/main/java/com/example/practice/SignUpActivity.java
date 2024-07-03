package com.example.practice;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSignUp;
    TextView tvLogin;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButton();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("email", email);
                contentValues.put("password", password);
                long id = db.insert("users", null, contentValues);
                if (id != -1) {
                    Toast.makeText(getApplicationContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void animateButton() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(btnSignUp, "translationY", 0f, 30f);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

}
