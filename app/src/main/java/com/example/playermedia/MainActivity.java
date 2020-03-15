package com.example.playermedia;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
TextView textView;

 String[] nameOfSong;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runtime();
       /* ArrayList<HashMap<String, String>> songlist = getPlayList("/storage/sdcard1/");
        if (songlist != null) {
            for (int i = 0; i < songlist.size(); i++) {
                String fileName = songlist.get(i).get("File_name");
                String filepath = songlist.get(i).get("File_path");
                Toast.makeText(this, fileName + filepath, Toast.LENGTH_SHORT).show();
            }
        }*/
             ArrayList<File>sdsongs=new ArrayList<File>();
             sdsongs=read_song(getExternalFilesDir(Environment.DIRECTORY_MUSIC));
                int len1=sdsongs.size();
                nameOfSong=new String[sdsongs.size()];
                for(int i=0;i<len1;i++){
                    nameOfSong[i]=sdsongs.get(i).getName().toString();
                }
              if(len1==0){
                  Toast.makeText(MainActivity.this,"not working",Toast.LENGTH_SHORT).show();
              }
      final  ArrayList<String> song = new ArrayList<String>();
        song.add("song1");
        song.add("song2");
        song.add("song3");

        int len2=song.size();
       //ArrayList<String>result=new ArrayList<String>(len2+len1);
        //System.arraycopy(song,0,result,0,len2);
        //System.arraycopy(nameOfSong,0,result,len2,len1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, song);
        ListView listview;
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter((adapter));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         Intent intent=new Intent(MainActivity.this,Music.class);
                         intent.putExtra("position",i);
                         //intent.putExtra("List",song);
                          startActivity(intent);
            }

        });

    }
    public void runtime(){
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                           token.continuePermissionRequest();
                    }
                }).check();
    }
  ArrayList<File>read_song(File root){
        ArrayList<File>arrayList=new ArrayList<File>();
        File[]todo=root.listFiles();
        for(File x:todo){
            if(x.isDirectory()){
                arrayList.addAll(read_song(x));
            }
            else{
               if(x.getName().endsWith(".mp3")){
                   arrayList.add(x);
               }


            }
        }
      return arrayList;
  }
 /*ArrayList<HashMap<String,String>>getPlayList(String rootPath){
        ArrayList<HashMap<String,String>>fileList=new ArrayList<>();
        try{
            File rootFolder =new File(rootPath);
            File[]files=rootFolder.listFiles();
            for(File file:files){
                if(file.isDirectory()){
                    if(getPlayList (file.getAbsolutePath())!=null){
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    }
                    else{break;}
                }
                else if(file.getName().endsWith(".mp3")){
                    HashMap<String,String> song = new HashMap<>();
                    song.put("File_name",file.getName());
                    song.put("File_path",file.getAbsolutePath());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e){
            return null;
        }
}
   /* public void showCinemas(HashMap<String, String> cinemas) {
        ArrayAdapter MyAdapter;
        MyAdapter adapter = new MyAdapter(cinemas);
        list.setAdapter(adapter);
    }*/
}
