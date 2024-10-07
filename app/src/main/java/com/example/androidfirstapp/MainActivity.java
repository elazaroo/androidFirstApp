package com.example.androidfirstapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etCode, etDesc, etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCode = findViewById(R.id.etCode);
        etDesc = findViewById(R.id.etDesc);
        etPrice = findViewById(R.id.etPrice);
    }

    public void register(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String code = etCode.getText().toString();
        String description = etDesc.getText().toString();
        String price = etPrice.getText().toString();
        ContentValues record  = new ContentValues();
        record.put("code", Integer.parseInt(code));
        record.put("description", description);
        record.put("price", Double.parseDouble(price));
        db.insert("articles", null, record);
        db.close();
        etCode.setText("");
        etDesc.setText("");
        etPrice.setText("");
        Toast.makeText(this, "Loaded article data", Toast.LENGTH_SHORT).show();
    }

    public void searchByCode(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        int code = Integer.parseInt(etCode.getText().toString());
        Cursor row = db.rawQuery("select description, price from articles where code = " + code, null);
        if (row.moveToFirst()) {
            etDesc.setText(row.getString(0));
            etPrice.setText(row.getString(1));
        } else {
            Toast.makeText(this, "Code not found",
                    Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void searchByDesc(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String desc = etDesc.getText().toString();
        Cursor row = db.rawQuery("select code, price from articles where description = " + desc, null);
        if (row.moveToFirst()) {
            etCode.setText(row.getString(0));
            etPrice.setText(row.getString(1));
        } else {
            Toast.makeText(this, "Description not found",
                    Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void unsuscribeByCode(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String code = etCode.getText().toString();
        int ammount = db.delete("articles", "code=" + code, null);
        db.close();
        etCode.setText("");
        etDesc.setText("");
        etPrice.setText("");
        if (ammount == 1)
            Toast.makeText(this, "Article deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Article not found", Toast.LENGTH_SHORT).show();
    }

    public void modify(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String code = etCode.getText().toString();
        String desc = etDesc.getText().toString();
        String price = etPrice.getText().toString();
        ContentValues record = new ContentValues();
        record.put("code", code);
        record.put("description", desc);
        record.put("price", Double.parseDouble(price));
        int cant = db.update("articles", record, "code=" + code, null);
        db.close();
        if (cant == 1)
            Toast.makeText(this, "Data modified", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Article not found", Toast.LENGTH_SHORT).show();
    }
}