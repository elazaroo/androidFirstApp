package com.example.androidfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv1;
    private RadioButton r1, r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etInput1);
        et2 = findViewById(R.id.etInput2);
        tv1 = findViewById(R.id.tvResult);
        r1 = findViewById(R.id.rbSum);
        r2 = findViewById(R.id.rbRest);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void operate(View v) {
        String val1 = et1.getText().toString();
        String val2 = et2.getText().toString();

        int num1 = Integer.parseInt(val1);
        int num2 = Integer.parseInt(val2);

        if (r1.isChecked()) {
            int sum = num1 + num2;
            String result =  String.valueOf(sum);
            tv1.setText(result);
        } else if (r2.isChecked()){
            int rest = num1 - num2;
            String result =  String.valueOf(rest);
            tv1.setText(result);
        }
    }
}