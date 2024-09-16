package com.example.androidfirstapp;

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
    private EditText etPassword,etRePassword;

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
            return insets;
        });
    }

    public void verify(View v)
    {
        String pass = etPassword.getText().toString();
        if (pass.length()==0) {
            Toast notificacion= Toast.makeText(this,"Password can't be empty", Toast.LENGTH_LONG);
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
        Toast notificacion= Toast.makeText(this,"Passwords don't match", Toast.LENGTH_LONG);
        notificacion.show();
    }
}
}