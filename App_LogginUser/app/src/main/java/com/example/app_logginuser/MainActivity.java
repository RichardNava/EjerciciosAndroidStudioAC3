package com.example.app_logginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etUser, etPass;
    DaoUser dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText) findViewById(R.id.edUser);
        etPass = (EditText) findViewById(R.id.edPass);
        dao = new DaoUser(this);
    }

    public void enter(View v){
        String u = etUser.getText().toString();
        String p = etPass.getText().toString();
        if(u.equals("")&&p.equals("")) Toast.makeText(this, "Rellene los campos", Toast.LENGTH_SHORT).show();
        else if(dao.login(u,p)==1){
            User user = dao.getUser(u,p);
            Toast.makeText(this, "Acceso correcto", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,HomeActivity.class);
            i.putExtra("Id",user.getId());
            startActivity(i);
        }
    }
    public void register(View v){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
        finish();
    }
}