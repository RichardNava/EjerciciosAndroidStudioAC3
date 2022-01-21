package com.example.hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isStarted = false;
    private int counter = 0;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.tv_count);
    }

    public void startCount(View view){
        if(!isStarted) { // Paso 4
            isStarted = true;
        /* Paso 1
            while (isStarted) {
                counter++;

                text.setText(String.valueOf(counter));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
         */
                     /* Paso 2
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isStarted) {
                        counter++;
                        //Esto asi da fallo - Paso 2.5
                        text.setText(String.valueOf(counter));
                        //Esto asi funciona - Paso 3
                        runOnUiThread(new Runnable() { // Esto es necesario cuando queremos interactuar con procesos de la interfaz grafica/usuario (UI)
                            @Override
                            public void run() {
                                text.setText(String.valueOf(counter));
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start(); */
        }
    }
}