package com.toni.peeperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private View btn;

    private final String SALUDO = "Hi there!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonMain);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acceder al segundo activity y mandar un string
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("saludo", SALUDO);
                startActivity(intent);
            }
        });

    }
}