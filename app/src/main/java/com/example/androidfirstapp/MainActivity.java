package com.example.androidfirstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private char lastChar = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // firstVal = findViewById(R.id.etFirstValue);

            return insets;
        });
    }

    public void pressedButton(View view) {
        TextView tvScreen = view.getRootView().findViewById(R.id.tvScreen);
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (tvScreen != null && tvScreen.length() != 16) { // Max 17 chars added
            String currentText = tvScreen.getText().toString();
            char lastCharInText = currentText.isEmpty() ? ' ' : currentText.charAt(currentText.length() - 1);

            if (isOperator(buttonText.charAt(0))) {
                if (currentText.isEmpty() || isOperator(lastCharInText)) {
                    // Don't add the operator if the text is empty or the last character is an operator
                    return;
                }
            }

            tvScreen.append(buttonText);
            lastChar = buttonText.charAt(0); // Update last character
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '÷';
    }

    public void clearAll(View view) {
        TextView tvScreen = view.getRootView().findViewById(R.id.tvScreen);
        if (tvScreen != null) {
            tvScreen.setText("");
        }
    }

    public void clearLast(View view) {
        TextView tvScreen = view.getRootView().findViewById(R.id.tvScreen);
        if (tvScreen != null) {
            String currentText = tvScreen.getText().toString();
            if (!currentText.isEmpty()) {
                tvScreen.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
    }
}