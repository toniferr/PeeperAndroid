package com.toni.peeperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private View btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonMain);

        btn.setOnClickListener(this);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsaste el botón!", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this, "Pulsaste el botón!", Toast.LENGTH_SHORT).show();
    }
}