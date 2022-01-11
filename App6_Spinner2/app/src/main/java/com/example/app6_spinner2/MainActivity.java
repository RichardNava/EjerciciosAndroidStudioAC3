package com.example.app6_spinner2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etNum1, etNum2;
    private TextView tvResult;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText) findViewById(R.id.etn_num1);
        etNum2 = (EditText) findViewById(R.id.etn_num2);
        tvResult = (TextView) findViewById(R.id.tv_result);
        spinner = (Spinner) findViewById(R.id.spinner);

        String [] options = {"Elija una operación","Sumar","Restar","Multiplicar","Dividir"};
        // Clase ArrayAdapter que sirve para manejar las Array/Matriz/Arreglo
        // El spinner por defecto -> R.layout.support_simple_spinner_dropdown_item // simple_spinner_item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_2, options);
        spinner.setAdapter(adapter); // Asigno mi adaptador al spinner
    }

    public void Calcular(View v){
        float num1 = 0;
        float num2 = 0;
        try { // Este try/catch es innecesario porque los componentes ya nos restringen la entrada de datos
            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(this, "Debes escribir numeros", Toast.LENGTH_SHORT).show();
        }
        DecimalFormat df = new DecimalFormat("#.00"); // Este objeto de la clase DecimalFormat sirve para modificar la salida de los números decimales

        String select = spinner.getSelectedItem().toString(); // Es el método que me devuelve la opción seleccionada
        String result = "";

        if (select.equals("Sumar")){ // select == "Sumar" -> num1 == num2 // num1 != num
            result = df.format(num1+num2);
        }
        else if(select.equals("Restar")){
            result = df.format(num1-num2);
        }
        else if(select.equals("Multiplicar")){
            result = df.format(num1*num2);
        }
        else if(select.equals("Dividir")){
            if (num2 != 0){
                result = df.format(num1/num2);
            }
            else{
                result = "No se puede dividir entre 0";
            }
            /*
            try {
                result = df.format(String.valueOf(num1/num2));
            }
            catch (Exception e){
                result = "No se puede dividir entre 0";
            }
            */
        }
        else{
            result = "Debe seleccionar una opción";
        }
        tvResult.setText(result);
    }
}