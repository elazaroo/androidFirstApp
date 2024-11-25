package com.example.androidfirstapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvLogin;
    private EditText etName, etSurnames, etEmail, etPassword, etPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvLogin = findViewById(R.id.tvLogin);
        etName = findViewById(R.id.etName);
        etSurnames = findViewById(R.id.etSurnames);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);

    }

    public void login(View view) {
        // open MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        if (samePassword()) {
            if (!validEmail()) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            } else {
                if (emailInUse()) {
                    Toast.makeText(this, "Email already in use", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if etName and etSurnames are not empty
                    if (etName.getText().toString().isEmpty() || etSurnames.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Name and surnames cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
                        SQLiteDatabase db = admin.getReadableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("NAME", etName.getText().toString());
                        values.put("SURNAMES", etSurnames.getText().toString());
                        values.put("EMAIL", etEmail.getText().toString());
                        values.put("PASSWORD", etPassword.getText().toString());
                        db.insert("USERS", null, values);
                        db.close();
                        Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                    }
                }
            }
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean samePassword() {
        return etPassword.getText().toString().equals(etPassword2.getText().toString());
    }

    public boolean validEmail() {
        return etEmail.getText().toString().contains("@");
    }

    public boolean emailInUse() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        String email = etEmail.getText().toString();

        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE EMAIL = ?", new String[]{email});

        boolean exists = false;

        exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }
}