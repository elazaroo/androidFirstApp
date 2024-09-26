package com.example.androidfirstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv1;
    private CheckBox c1, c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etInput1);
        et2 = findViewById(R.id.etInput2);
        tv1 = findViewById(R.id.tvResult);
        c1 = findViewById(R.id.cbSum);
        c2 = findViewById(R.id.cbRest);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("SetTextI18n")
    public void operate(View v) {
        String val1 = et1.getText().toString();
        String val2 = et2.getText().toString();

        int num1 = Integer.parseInt(val1);
        int num2 = Integer.parseInt(val2);

        if (c1.isChecked() && !c2.isChecked()) {
            int sum = num1 + num2;
            String result =  String.valueOf(sum);
            tv1.setText(result);
        } else if (c2.isChecked() && !c1.isChecked()) {
            int rest = num1 - num2;
            String result =  String.valueOf(rest);
            tv1.setText(result);
        } else if (c1.isChecked() && c2.isChecked()) {
            int sum = num1 + num2;
            int rest = num1 - num2;
            String result =  String.valueOf(sum) + " \n" + String.valueOf(rest);
            tv1.setText(result);
        } else {
            tv1.setText("No operation selected");
        }
    }
}