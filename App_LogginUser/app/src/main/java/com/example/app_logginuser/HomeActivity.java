package com.example.app_logginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private int id=0;
    private User user;
    private DaoUser dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new DaoUser(this);
        user= dao.getUserById(id);
        tvWelcome.setText("Bienvenido " +user.getNombre()+" "+user.getApellidos());

    }
}