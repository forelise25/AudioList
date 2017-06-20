package com.example.lg.audiolist;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button but_play, but_stop, but_pause;
    TextView textMusic;
    ProgressBar progress;
    String[] musics= {"너라서 - 공유","payphone - maroon5","sugar - maroon5"};
    int[] musicResIds={R.raw.gongyou, R.raw.payphone,R.raw.sugar};
    int selectedMusicId;
    MediaPlayer mediaPlayer;
    boolean selectedPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.list_music);
        but_play=(Button)findViewById(R.id.but_play);
        but_stop=(Button)findViewById(R.id.but_stop);
        but_pause=(Button)findViewById(R.id.but_pause);
        textMusic=(TextView)findViewById(R.id.text_music);
        progress=(ProgressBar)findViewById((R.id.progress_music));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, musics);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setItemChecked(0,true);
        selectedMusicId=musicResIds[0];
        mediaPlayer=MediaPlayer.create(this, selectedMusicId);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer.stop();
                selectedMusicId=musicResIds[i];
                progress.setVisibility(View.INVISIBLE);
            }
        });
        but_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPauseButton=false;
                if(selectedPauseButton) {
                    mediaPlayer.start();
                    selectedPauseButton=false;
                }else{
                    mediaPlayer=MediaPlayer.create(MainActivity.this,selectedMusicId);
                }
                mediaPlayer.start();
                progress.setVisibility(View.VISIBLE);
            }
        });
        but_stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                selectedPauseButton=false;
                progress.setVisibility(View.INVISIBLE);
            }
        });
        but_pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selectedPauseButton=true;
                mediaPlayer.pause();
                progress.setVisibility(View.INVISIBLE);
            }
        });

    }
    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.stop();
    }
}
