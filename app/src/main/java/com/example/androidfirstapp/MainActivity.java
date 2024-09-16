package com.example.androidfirstapp;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
{
    private EditText etPassword, etRePassword, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            etPassword = findViewById(R.id.etPassword);
            etRePassword = findViewById(R.id.etRePassword);
            etEmail = findViewById(R.id.etEmail);

            setHintBehavior(etEmail, R.string.email);
            setHintBehavior(etPassword, R.string.password);
            setHintBehavior(etRePassword, R.string.repeat_password);

            return insets;
        });
    }

    public void verify(View v)
    {
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        // Email not empty
        if (email.isEmpty()) {
            Toast notificacion = Toast.makeText(this, getString(R.string.email_cant_be_empty), Toast.LENGTH_LONG);
            notificacion.show();
        }
        // Pass not empty
        if (pass.isEmpty()) {
            Toast notificacion = Toast.makeText(this, getString(R.string.pass_cant_be_empty), Toast.LENGTH_LONG);
            notificacion.show();
        }
        // Minimum 6 length
        if (pass.length()<6) {
            Toast notificacion= Toast.makeText(this,getString(R.string.pass_must_be_at_least_6_chars), Toast.LENGTH_LONG);
            notificacion.show();
        }
        // Minumum 1 number, 1 uppercase, 1 lowercase, 1 special character
        if (!pass.matches(".*\\d.*") || !pass.matches(".*[A-Z].*") || !pass.matches(".*[a-z].*")) {
    Toast notificacion = Toast.makeText(this, getString(R.string.pass_requisites), Toast.LENGTH_LONG);
    notificacion.show();
}
        verifyRePassword(v);
    }

    public void verifyRePassword(View v)
    {
        String pass = etPassword.getText().toString();
        String Repass = etRePassword.getText().toString();

        if (!pass.equals(Repass))
        {
            Toast notificacion= Toast.makeText(this,getString(R.string.pass_dont_match), Toast.LENGTH_LONG);
            notificacion.show();
        }
    }

    private void setHintBehavior(EditText editText, int hintResId) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText.setHint("");
                } else {
                    if (editText.getText().toString().isEmpty()) {
                        editText.setHint(hintResId);
                    }
                }
            }
        });
    }

}