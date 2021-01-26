package com.toni.peeperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;

    private List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = (GridView) findViewById(R.id.gridView);

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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GridActivity.this, "clikeado: "+names.get(i), Toast.LENGTH_LONG).show();
            }
        });

        //enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter = new MyAdapter(this, R.layout.grid_item, names);

        gridView.setAdapter(myAdapter);
    }
}