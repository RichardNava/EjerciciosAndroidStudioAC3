package com.example.app21_grabadora2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder rec;
    private String outputFile = null;
    private ImageButton btnRec, btnPlay;
    private SoundPool sp;
    private int repSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRec = (ImageButton) findViewById(R.id.ibRec);
        btnPlay = (ImageButton) findViewById(R.id.ibPlay);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            sp = new SoundPool.Builder().setMaxStreams(1).build();
        }
        else{
            sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        }
        repSound = sp.load(this,R.raw.sound_short,1);

        // PERMISOS PARA GRABAR AUDIO
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO},1000);
        }
    }


    @SuppressLint("WrongConstant")
    public void grabacion(View v){
        sp.play(repSound,1,1,1,0,0);
        if (rec == null){
            outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.mp3";
            rec = new MediaRecorder();
            rec.setAudioSource(MediaRecorder.AudioSource.MIC);

            // Definir formato de salida, definir tipo de codificacion, indicar ruta donde se generara el archivo
            rec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // Estas son las opciones que le funcionan al profe, probar y cambiar si no bien
            rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // Estas son las opciones que le funcionan al profe, probar y cambiar si no bien
            rec.setOutputFile(outputFile);

            try {
                rec.prepare();
                rec.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            btnRec.setImageResource(R.drawable.rec);
            Toast.makeText(this, "Grabando audio...", Toast.LENGTH_LONG).show();
        }
        else if(rec != null){
            rec.stop();
            rec.release();
            rec = null;
            // Método para cambiar imagen
            btnRec.setImageResource(R.drawable.stop_rec);
            Toast.makeText(this, "Grabación finalizada", Toast.LENGTH_SHORT).show();

        }
    }
    public void reproducir(View v){
        sp.play(repSound,1,1,1,0,0);
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource(outputFile);
            mp.prepare();
            mp.start();
            Toast.makeText(this, "Reproduciendo Audio...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}