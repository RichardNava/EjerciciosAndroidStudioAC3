package com.example.app22_reproductoraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton playPause, btnLoop;
    private MediaPlayer mp;
    private ImageView cover;
    private int repeat = 2, pos= 0;

    MediaPlayer vectorMp []  = new MediaPlayer[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPause = (ImageButton) findViewById(R.id.btnPlay);
        btnLoop = (ImageButton) findViewById(R.id.btnLoop);
        cover = (ImageView) findViewById(R.id.ivCover);

        reset(); // Cargamos nuestras canciones dentro de la matriz
    }

    public void playPause(View v){
        // If = pasamos al modo pause
        if(vectorMp[pos].isPlaying()){ //Con el método isPlaying nos devuelve un booleano con true si la canción se esta reproduciendo
            vectorMp[pos].pause();
            playPause.setImageResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();

        }
        else{ // else = pasamos al modo reproducción
            if (pos == 0){
                cover.setImageResource(R.drawable.port_acdc);
            }
            vectorMp[pos].start();
            playPause.setImageResource(R.drawable.pausa);
            Toast.makeText(this, "Reproducir", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View v){
        if(vectorMp[pos] != null){ // Indicamos que hay una canción disponible
            vectorMp[pos].stop();
            // Tenemos que indicar las canciones que hay en el Array
            reset();
            pos=0;
            playPause.setImageResource(R.drawable.reproducir);
            cover.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }
    public void loop(View v){
        if (repeat == 1){ // modo 2 = No repito la canción, modo 1 = repito la canción
            btnLoop.setImageResource(R.drawable.no_repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectorMp[pos].setLooping(true);
            repeat = 2;
        }
        else{
            btnLoop.setImageResource(R.drawable.repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            vectorMp[pos].setLooping(false);
            repeat = 1;
        }
    }

    public void next(View v){
        if(pos < vectorMp.length-1){
            if (vectorMp[pos].isPlaying()){
                vectorMp[pos].stop();
                pos++; // Equivale a pos = pos + 1;
                vectorMp[pos].start();
                selectCover();
            }
            else{
                pos++; // Equivale a pos = pos + 1;
                //vectorMp[pos].start();
                selectCover();
            }
        }
        else{
            Toast.makeText(this, "No hay más canciones en la lista de reproducción", Toast.LENGTH_SHORT).show();
        }
    }

    public void prev(View v){
        if(pos >= 1){
            if (vectorMp[pos].isPlaying()){
                vectorMp[pos].stop();
                reset();
                pos--; // Equivale a pos = pos - 1;
                selectCover();
                vectorMp[pos].start();
            }
            else{
                pos--; // Equivale a pos = pos - 1;
                //vectorMp[pos].start();
                selectCover();
            }
        }
        else{
            Toast.makeText(this, "Esta es la primera canción de la lista.", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectCover(){
        if(pos == 0){
            cover.setImageResource(R.drawable.port_acdc);
        }
        else if(pos == 1){
            cover.setImageResource(R.drawable.port_alice);
        }
        else if(pos == 2){
            cover.setImageResource(R.drawable.port_conga);
        }
        else if(pos == 3){
            cover.setImageResource(R.drawable.port_talco);
        }

    }

    public void reset(){ // Método para cargar las canciones
        vectorMp[0] = MediaPlayer.create(this,R.raw.acdc_thunderstruck);
        vectorMp[1] = MediaPlayer.create(this,R.raw.alice_francis_shoot_him_down);
        vectorMp[2] = MediaPlayer.create(this,R.raw.conga_gloria_estefan);
        vectorMp[3] = MediaPlayer.create(this,R.raw.talco_danza_dellautunno_rosa);

    }

}