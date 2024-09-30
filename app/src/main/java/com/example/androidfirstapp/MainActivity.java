package com.example.androidfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etInput);

        num=(int)(Math.random()*100001);
        String collection = String.valueOf(num);
        Toast notification = Toast.makeText(this,collection,Toast.LENGTH_LONG);
        notification.show();
    }

    public void submit(View view) {
        String enteredValue = et1.getText().toString();
        int value = Integer.parseInt(enteredValue);
        if (value==num) {
            Toast notification=Toast.makeText(this,"Correct!.",Toast.LENGTH_LONG);
            notification.show();
        }
        else {
            Toast notification=Toast.makeText(this,"Incorrect.", Toast.LENGTH_LONG);
            notification.show();
        }
    }
}