package com.example.parcial_dama.controllers;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parcial_dama.R;
import com.example.parcial_dama.models.Song;

import java.io.IOException;
import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>
        implements View.OnClickListener{

    private ArrayList<Song> songs;
    private Context context;
    private View.OnClickListener clickListener;
    private ImageView imageViewSong;
    private TextView textViewSongName;
    private TextView textViewArtistName;
    private TextView textViewAlbumName;
    private TextView textViewNowPlaying;
    private MediaPlayer mediaPlayer;
    private ImageButton mainActivityPlayPauseButton;
    public MusicAdapter(ArrayList<Song> listaMusica, Context context,  ImageView imageViewSong, TextView textViewSongName, TextView textViewArtistName, TextView textViewNowPlaying, TextView textViewAlbumName, ImageButton mainActivityPlayPauseButton) {
        this.context = context;
        this.songs = listaMusica;
        this.imageViewSong = imageViewSong;
        this.textViewSongName = textViewSongName;
        this.textViewArtistName = textViewArtistName;
        this.textViewAlbumName = textViewAlbumName;
        this.textViewNowPlaying = textViewNowPlaying;
        this.mainActivityPlayPauseButton = mainActivityPlayPauseButton;
        this.mediaPlayer = new MediaPlayer();

    }

    public void setOnClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
    @Override
    public void onClick(View v) {
        if(clickListener != null){
            clickListener.onClick(v);
        }
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_song, null, false);
        return new MusicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.textViewSongName.setText(song.getName());
        holder.textViewAlbumName.setText(song.getAlbum());
        holder.imageViewSong.setImageResource(song.getImageResource());
        holder.imageButtonPlay.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                holder.imageButtonPlay.setImageResource(R.drawable.boton_reproducir);
                mainActivityPlayPauseButton.setImageResource(R.drawable.boton_reproducir);
            } else {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(context, Uri.parse("android.resource://" + context.getPackageName() + "/" + song.getAudioResource()));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    holder.imageButtonPlay.setImageResource(R.drawable.boton_pausar);
                    mainActivityPlayPauseButton.setImageResource(R.drawable.boton_pausar);

                    imageViewSong.setImageResource(song.getImageResource());
                    textViewSongName.setText(song.getName());
                    textViewArtistName.setText(song.getArtist());
                    textViewNowPlaying.setText("Reproduciendo " + song.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
    public void PlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mainActivityPlayPauseButton.setImageResource(R.drawable.boton_reproducir);
        } else {
            mediaPlayer.start();
            mainActivityPlayPauseButton.setImageResource(R.drawable.boton_pausar);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewSong;
        TextView textViewSongName;
        TextView textViewArtistName;
        TextView textViewAlbumName;
        ImageButton imageButtonPlay;
        public ViewHolder(View itemview) {
            super(itemview);
            imageViewSong = itemView.findViewById(R.id.imageViewSong);
            textViewSongName = itemView.findViewById(R.id.textViewSongName);
            textViewArtistName = itemView.findViewById(R.id.textViewArtistName);
            imageButtonPlay = itemView.findViewById(R.id.imageButtonPlay);
            textViewAlbumName = itemview.findViewById(R.id.textViewAlbumName);
        }
    }
}
