package com.toni.peeperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textViewMain);

        // Toma datos del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("saludo") != null){
            String saludo = bundle.getString("saludo");
            Toast.makeText(SecondActivity.this, saludo, Toast.LENGTH_LONG).show();
            textView.setText(saludo);
        } else {
            Toast.makeText(SecondActivity.this, "vacío", Toast.LENGTH_LONG).show();
        }
    }
}