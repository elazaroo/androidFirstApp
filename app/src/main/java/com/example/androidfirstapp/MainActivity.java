package com.example.androidfirstapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvRegister;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegister = findViewById(R.id.tvRegister);
    }

    public void register(View view) {
        // open RegisterActivity
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
    // get username and password
    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    String email = etEmail.getText().toString();
    String pass = etPassword.getText().toString();

    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
    SQLiteDatabase db = admin.getReadableDatabase();

    Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?", new String[]{email, pass});
    if (cursor.moveToFirst()) {
        int idIndex = cursor.getColumnIndex("ID");
        if (idIndex != -1) {
            int id = cursor.getInt(idIndex);

            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            // open HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        } else {
            Toast.makeText(this, "ID column not found", Toast.LENGTH_SHORT).show();
        }
    } else {
        Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
    }

    cursor.close();
    db.close();
}
}