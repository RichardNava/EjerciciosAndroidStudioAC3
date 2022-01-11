package com.example.app3_estructuraifevaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etn1Mat, etn2Hist, etn3Fil;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etn1Mat = (EditText) findViewById(R.id.etn1_mat);
        etn2Hist = (EditText) findViewById(R.id.etn2_hist);
        etn3Fil = (EditText) findViewById(R.id.etn3_fil);
        tvResult = (TextView) findViewById(R.id.tv_result);

    }
    public void calcEvaluacion(View v){
        // Con la siguiente linea establecemos el patron del formato decimal que queremos en nuestro números
        DecimalFormat df = new DecimalFormat("#.0");
        String matText = etn1Mat.getText().toString();
        String histText = etn2Hist.getText().toString();
        String filText = etn3Fil.getText().toString();

        float matFloat = Float.parseFloat(matText);
        float histFloat = Float.parseFloat(histText);
        float filFloat = Float.parseFloat(filText);

        float promedio = (matFloat+histFloat+filFloat)/3;

        if (promedio >= 5) {
            Toast.makeText(this, "Estado: Alumno aprobado.", Toast.LENGTH_SHORT).show();
            tvResult.setText("Nota: " + df.format(promedio));
        }
        else {
            Toast.makeText(this, "Estado: Alumno suspendido.", Toast.LENGTH_SHORT).show();
            tvResult.setText("Nota: " + df.format(promedio));
        }

    }
}