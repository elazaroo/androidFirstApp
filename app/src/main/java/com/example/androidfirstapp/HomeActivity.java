package com.example.androidfirstapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcome = findViewById(R.id.tvWelcome);

        // Obtener el id como entero
        int userId = getIntent().getIntExtra("id", -1);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT NAME FROM USERS WHERE ID = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("NAME");
            String name = cursor.getString(nameIndex);
            // Mostrar el nombre en el TextView
            welcome.setText("Welcome " + name);
        } else {
            welcome.setText("User not found");
        }

        cursor.close();
        db.close();
    }

    public void newExpedient(View view) {
        Intent intent = new Intent(this, NewExpedientActivity.class);
        intent.putExtra("id", getIntent().getIntExtra("id", -1));
        startActivity(intent);
    }
}