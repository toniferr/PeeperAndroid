package com.toni.peeperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private View btn;

    private ListView listView;

    private final String SALUDO = "Hi there!";

    private List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //forzar y cargar icono en action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_mylauncher);

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

        listView = (ListView) findViewById(R.id.listView);

        //datos a mostrar
        names.add("Toni");
        names.add("Pepe");
        names.add("Manolo");
        names.add("Santiago");
        names.add("Pablo");
        names.add("Juan");
        names.add("Jose");
        names.add("Ramon");
        names.add("David");
        names.add("Diego");
        names.add("Alvaro");
        names.add("Jorge");
        names.add("Lucas");
        names.add("Gonzalo");
        names.add("Martin");

        //adaptador, la forma visual en que mostraremos los datos
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);

        //enlazar adaptador con listview
        //listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "clikeado: "+names.get(i), Toast.LENGTH_LONG).show();
            }
        });

        //enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);

        listView.setAdapter(myAdapter);

    }
}

