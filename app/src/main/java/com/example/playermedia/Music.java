package com.example.playermedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Music extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button play,prev,next,playlist;
    int pos,i=0;
    String allsongs[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
       final  Bundle bundle = getIntent().getExtras();
           int position=bundle.getInt("position");
          //for( i=0;allsongs[i]!=null;i++) {
            //  allsongs[i] = bundle.getString("List");
          //}

            pos=position;
        prev=(Button)findViewById(R.id.prev);
        next=(Button)findViewById(R.id.next);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                }
                pos=pos-1;
                //Toast.makeText(Music.this,"now playing",Toast.LENGTH_SHORT).show();
                switch (pos){
                    case 0: mediaPlayer=MediaPlayer.create(Music.this,R.raw.music);
                    break;
                    case 1: mediaPlayer=MediaPlayer.create(Music.this,R.raw.musical);
                    break;
                    case 2: mediaPlayer=MediaPlayer.create(Music.this,R.raw.music2);
                    break;
                    //default:Toast.makeText(Music.this,"NO SONGS FURTHER",Toast.LENGTH_SHORT).show();
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                }
                  pos=pos+1;
               // Toast.makeText(Music.this,"now playing",Toast.LENGTH_SHORT).show();
                switch (pos){
                    case 0: mediaPlayer=MediaPlayer.create(Music.this,R.raw.music);
                        break;
                    case 1: mediaPlayer=MediaPlayer.create(Music.this,R.raw.musical);
                        break;
                    case 2: mediaPlayer=MediaPlayer.create(Music.this,R.raw.music2);
                        break;
                   // default:Toast.makeText(Music.this,"NO SONGS FURTHER",Toast.LENGTH_SHORT).show();
                }
            }
        });
        playlist=(Button)findViewById(R.id.playlist);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Music.this,MainActivity.class);
   mediaPlayer.stop();
                startActivity(intent);
            }
        });
           if(pos==0){
              mediaPlayer= MediaPlayer.create(this,R.raw.music);
           }
           if(pos==1){
               mediaPlayer=MediaPlayer.create(this,R.raw.musical);
           }
           if(pos==2){
               mediaPlayer=MediaPlayer.create(this,R.raw.music2);
           }
            play=(Button)findViewById(R.id.play);
           play.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   //get song corresponding to this pos dont use swicth
                   if(i%2==0){
                     mediaPlayer.start();}
                    else{
                        mediaPlayer.pause();
                    }
                    i++;
               }
           });
          /* if(mediaPlayer!=null){
               mediaPlayer.stop();
               mediaPlayer.release();
           }*/
           final SeekBar seekBar;
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,2000);


    }
}