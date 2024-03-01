package com.example.parcial_dama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parcial_dama.controllers.MusicAdapter;
import com.example.parcial_dama.models.Song;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerMusic;
    private ArrayList<Song> songs;
    private ImageView imageViewSong;
    private TextView textViewSongName;
    private TextView textViewArtistName;
    private TextView textViewNowPlaying;
    private TextView textViewAlbumName;
    private ImageButton imageButtonPlayPause;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitComponents();
        addSoundList();
        setAdapterMusic();
    }

    private void setAdapterMusic() {
        MusicAdapter adapter = new MusicAdapter(songs, this, imageViewSong, textViewSongName, textViewArtistName, textViewNowPlaying, textViewAlbumName, imageButtonPlayPause);
        recyclerMusic.setAdapter(adapter);
    }

    private void addSoundList() {
        songs = new ArrayList<>();

        songs.add(new Song("HIBIKI", "Bad Bunny ft Mora", "Nadie sabe lo que va a pasar mañana", R.drawable.nadie_sabe, R.raw.hibiki));
        songs.add(new Song("Moscow Mule", "Bad Bunny ft Chencho","Un verano sin ti", R.drawable.baddd, R.raw.moscowmule));
        songs.add(new Song("Best you had", "Don Toliver","Best you had", R.drawable.best, R.raw.bestyouhad));
        songs.add(new Song("Sigo enamorau", "Eladio Carrion ft Rauw Alejandro","Sol Maria", R.drawable.sol_maria, R.raw.sigoenamorau));
        songs.add(new Song("n.h.i.e", "Savage","american dream", R.drawable.savageee, R.raw.savage));
    }

    public void togglePlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            imageButtonPlayPause.setImageResource(R.drawable.boton_reproducir);
        } else {
            mediaPlayer.start();
            imageButtonPlayPause.setImageResource(R.drawable.boton_pausar);
        }
        ((MusicAdapter) recyclerMusic.getAdapter()).PlayPause();
    }

    private void setInitComponents() {
        recyclerMusic = findViewById(R.id.recycler);
        recyclerMusic.setLayoutManager(new LinearLayoutManager(this));

        imageViewSong = findViewById(R.id.imageViewSong);
        textViewSongName = findViewById(R.id.textViewSongName);
        textViewArtistName = findViewById(R.id.textViewArtistName);
        textViewNowPlaying = findViewById(R.id.textViewNowPlaying);
        imageButtonPlayPause = findViewById(R.id.imageButtonPlayPause);
        mediaPlayer = new MediaPlayer();

        imageButtonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
    }
}