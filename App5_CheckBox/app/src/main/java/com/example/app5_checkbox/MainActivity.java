package com.example.app5_checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etnNum1,etnNum2;
    private CheckBox cbSum, cbRest, cbMult, cbDiv;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnNum1 = (EditText) findViewById(R.id.etn_num1);
        etnNum2 = (EditText) findViewById(R.id.etn_num2);
        cbSum = (CheckBox) findViewById(R.id.cb_sum);
        cbRest = (CheckBox) findViewById(R.id.cb_rest);
        cbMult = (CheckBox) findViewById(R.id.cb_mult);
        cbDiv = (CheckBox) findViewById(R.id.cb_div);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }
    // Método para calcular
    public void Calcular(View v){
        float num1 = Float.parseFloat(etnNum1.getText().toString());
        float num2 = Float.parseFloat(etnNum2.getText().toString());

        String result = "";
        /*
        // Tomamos como ejemplo un código en el que solo hubiese dos posibles operaciones
        if (cbSum.isChecked() && cbRest.isChecked()){ // && = AND
            // suma + resta
            result = "Suma: " + (num1+num2) + "\n" + "Resta: " + (num1-num2);
        } else if (cbSum.isChecked()){
            // suma
            result = "Suma: " + (num1+num2);
        } else if (cbRest.isChecked()){
            // resta
            result = "Resta: " + (num1-num2);
        } else{
            // nada
            result = "Debe seleccionar una opción";
        }
        */
         /*
        if(cbSum.isChecked() || cbRest.isChecked() || cbMult.isChecked() || cbDiv.isChecked()){} // ¿Hay alguna opción seleccionada?
        if(cbSum.isChecked() && cbRest.isChecked() && cbMult.isChecked() && cbDiv.isChecked()){} // ¿EStan todas las opciones seleccionadas?
        if((cbSum.isChecked() && cbRest.isChecked()) || (cbMult.isChecked() && cbDiv.isChecked())){} // ¿Estan las opciones suma y resta seleccionadas o multiplicacion y division?
        if((cbSum.isChecked() || cbRest.isChecked()) && ((cbMult.isChecked() || cbDiv.isChecked()))){} // ¿Estan seleccionadas alguna combinación entre suma/resta y multiplicación/división?
        if(6>8 || 5<2 || 4>=1 || 5<=8 || 1==1 || 2!=5) // Operadores relacionales
        */


        if(cbSum.isChecked() || cbRest.isChecked() || cbMult.isChecked() || cbDiv.isChecked()){ // || = OR
            if(cbSum.isChecked()) {
                result +=  "Suma: " + (num1 + num2) + "\n"; // result = result + "nuevo texto";
            }                                              // int numero = 2; -> numero = numero + 2; -> numero += 2;
            if(cbRest.isChecked()) {
                result += "Resta: " + (num1 - num2) + "\n";
                // result += cbMult.isChecked() ? "Resta: " + (num1 * num2) + "\n" : "";
            }
            if(cbMult.isChecked()) {
                result += "Multiplicación: " + (num1*num2) + "\n";
            }
            if(cbDiv.isChecked()){
                if (num2 != 0) {
                    result += "División: " + (num1 / num2);
                }
                else{
                    result += "División: No se puede dividir entre 0";
                }
            }
        }
        else{
            result = "No ha seleccionado ninguna operación";
        }




        tvResult.setText(result);

    }


}