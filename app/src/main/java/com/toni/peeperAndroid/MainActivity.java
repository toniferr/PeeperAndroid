package com.toni.peeperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void miMetodo(View view){
        Toast.makeText(this, "Pulsaste el botón!", Toast.LENGTH_SHORT).show();
    }
}