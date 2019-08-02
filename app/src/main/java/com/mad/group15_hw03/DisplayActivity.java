/*
Assignment : HomeWork 03
File Name : Group15_HW03.zip
Full Names : Manideep Reddy Nukala, Sai Charan Reddy Vallapureddy
 */
package com.mad.group15_hw03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayActivity extends AppCompatActivity {
    ImageView imageView;
    TextView trackValue;
    TextView genreValue;
    TextView artistValue;
    TextView albumValue;
    TextView trackPriceValue;
    TextView albumPriceValue;
    Button finishButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        setTitle("iTunes Music Search");
        imageView = findViewById(R.id.imageView);
        trackValue = findViewById(R.id.trackValue);
        genreValue = findViewById(R.id.genreValue);
        artistValue = findViewById(R.id.artistValue);
        albumValue = findViewById(R.id.albumValue);
        trackPriceValue = findViewById(R.id.trackPriceValue);
        albumPriceValue = findViewById(R.id.albumPriceValue);
        finishButton = findViewById(R.id.finishButton);

        Intent intent=getIntent();
        Album album = (Album)intent.getExtras().getSerializable(MainActivity.track_key);
        String track_name = album.trackName;
        String genre_name = album.primaryGenreName;
        String artist_name = album.artistName;
        String album_name = album.collectionName;
        String track_price = album.trackPrice;
        String album_price = album.collectionPrice;
        String url = album.artworkUrl100;
        Picasso.get().load(url).into(imageView);
        trackValue.setText(track_name);
        genreValue.setText(genre_name);
        artistValue.setText(artist_name);
        albumValue.setText(album_name);
        trackPriceValue.setText(String.valueOf(track_price)+" $");
        albumPriceValue.setText(String.valueOf(album_price)+" $");

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}
