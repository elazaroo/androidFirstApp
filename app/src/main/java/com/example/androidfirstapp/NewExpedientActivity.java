package com.example.androidfirstapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewExpedientActivity extends AppCompatActivity {

    private EditText reference, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expedient);

        reference = findViewById(R.id.etReference);
        user = findViewById(R.id.etUser);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT NAME, SURNAMES FROM USERS WHERE ID = ?", new String[]{String.valueOf(getIntent().getIntExtra("id", -1))});
        cursor.moveToFirst();
        int nameIndex = cursor.getColumnIndex("NAME");
        int surnamesIndex = cursor.getColumnIndex("SURNAMES");
        String userNameSurname = cursor.getString(nameIndex) + " " + cursor.getString(surnamesIndex);
        user.setText(userNameSurname);

        cursor.close();
        db.close();
    }

    public void saveExpedient(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String referenceText = reference.getText().toString();
        int userId = getIntent().getIntExtra("id", -1);

        // Inserta el expediente
        db.execSQL("INSERT INTO EXPEDIENT(REFERENCE, FINISHED) VALUES(?, 0);", new Object[]{referenceText});

        // Obtiene el último ID generado
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid() AS ID", null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("ID");
            if (idIndex != -1) {
                int expedientId = cursor.getInt(idIndex);

                // Verifica si la entrada ya existe
                Cursor checkCursor = db.rawQuery("SELECT * FROM USER_EXPEDIENT WHERE USER_ID = ? AND EXPEDIENT_ID = ?", new String[]{String.valueOf(userId), String.valueOf(expedientId)});
                if (!checkCursor.moveToFirst()) {
                    db.execSQL("INSERT INTO USER_EXPEDIENT(USER_ID, EXPEDIENT_ID) VALUES(?, ?);", new Object[]{userId, expedientId});
                    Toast.makeText(this, "Expedient created, ID = " + expedientId, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Entry already exists", Toast.LENGTH_SHORT).show();
                }
                checkCursor.close();
            } else {
                Toast.makeText(this, "ID column not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Expedient not found", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }

}