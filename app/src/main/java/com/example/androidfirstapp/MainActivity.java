package com.example.androidfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv1;
    private Spinner s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etInput1);
        et2 = findViewById(R.id.etInput2);
        tv1 = findViewById(R.id.tvResult);
        s1 = findViewById(R.id.spinner);

        String[] options = {"Sumar", "Restar", "Multiplicar", "Dividir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        s1.setAdapter(adapter);

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

        String select = s1.getSelectedItem().toString();
        if (select.equals("Sumar")) {
            int sum = num1 + num2;
            tv1.setText("Resultado: " + sum);
        } else if (select.equals("Restar")) {
            int res = num1 - num2;
            tv1.setText("Resultado: " + res);
        } else if (select.equals("Multiplicar")) {
            int mul = num1 * num2;
            tv1.setText("Resultado: " + mul);
        } else if (select.equals("Dividir")) {
            if (num2 != 0) {
                int div = num1 / num2;
                tv1.setText("Resultado: " + div);
            } else {
                tv1.setText("No se puede dividir entre 0");
            }
        }
    }
}