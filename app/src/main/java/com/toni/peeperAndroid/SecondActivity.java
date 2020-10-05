package com.toni.peeperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textViewMain);
        btnNext = (Button) findViewById(R.id.buttonCompartir);

        // Toma datos del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("saludo") != null){
            String saludo = bundle.getString("saludo");
            Toast.makeText(SecondActivity.this, saludo, Toast.LENGTH_LONG).show();
            textView.setText(saludo);
        } else {
            Toast.makeText(SecondActivity.this, "vacío", Toast.LENGTH_LONG).show();
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}