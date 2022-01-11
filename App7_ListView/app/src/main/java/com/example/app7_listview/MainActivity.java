package com.example.app7_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ListView lvNombres;

    private String [] nombres = {"Santiago","Ramon","Laura","Vanesa","Leticia","Pedro"};
    private String [] años = {"20","34","16","45","28","64"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        lvNombres = (ListView) findViewById(R.id.lv_nombres);
            // Argumentos del constructor de ArrayAdapter -> contexto, layout (patron de diseño), coleccion que vamos a manejar
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_item_ac3, nombres);
        lvNombres.setAdapter(adapter);

        // TODO: Clase anonima en setOnClickListener

    }

}