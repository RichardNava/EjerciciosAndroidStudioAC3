package com.example.app_logginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUser, etPass, etName, etAp;
    DaoUser dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = (EditText) findViewById(R.id.edUser);
        etPass = (EditText) findViewById(R.id.edPass);
        etName = (EditText) findViewById(R.id.etName);
        etAp = (EditText) findViewById(R.id.etApellidos);

        dao = new DaoUser(this);
    }

    public void register(View v){
        User u = new User();
        u.setUsuario(etUser.getText().toString());
        u.setPassword(etPass.getText().toString());
        u.setNombre(etName.getText().toString());
        u.setApellidos(etAp.getText().toString());
        if (!u.isNull()){
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
        else if(dao.insertUser(u)){
            Toast.makeText(this, "Registro realizado con éxito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usario ya registrado", Toast.LENGTH_SHORT).show();
        }
    }
    public void back(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}