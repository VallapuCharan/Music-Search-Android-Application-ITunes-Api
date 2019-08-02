/*
Assignment : HomeWork 03
File Name : Group15_HW03.zip
Full Names : Manideep Reddy Nukala, Sai Charan Reddy Vallapureddy
 */
package com.mad.group15_hw03;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText searchEditText;
    TextView limitTextView;
    SeekBar limitSeekBar;
    Button searchButton;
    Button resetButton;
    TextView sortTextView;
    TextView priceTextView;
    Switch sortSwitch;
    TextView dateTextView;
    TextView resultsTextView;
    ListView resultsListView;
    ProgressBar progressBar;
    ArrayList trackArrayList;
    AlbumAdapter albumAdapter;
    String sortType="Date";
    double limit_value;
    double limit_min = 10;
    double limit_step = 1;
    double limit_max = 30;
    public static final String track_key="Album Details";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("iTunes Music Search");
        searchEditText = findViewById(R.id.searchEditText);
        limitTextView = findViewById(R.id.limitTextView);
        limitSeekBar = findViewById(R.id.limitSeekBar);
        searchButton = findViewById(R.id.searchButton);
        resetButton = findViewById(R.id.resetButton);
        sortTextView = findViewById(R.id.sortTextView);
        priceTextView = findViewById(R.id.priceTextView);
        sortSwitch = findViewById(R.id.sortSwitch);
        dateTextView = findViewById(R.id.dateTextView);
        resultsTextView = findViewById(R.id.resultsTextView);
        resultsListView = findViewById(R.id.resultsListView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        limitSeekBar.setMax((int) (limit_max-limit_min));

        limitSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                limit_value = limit_min + (progress * limit_step);
                limitTextView.setText("Limit: "+(int) limit_value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String searchWord = searchEditText.getText().toString();
                    String url="https://itunes.apple.com/search?term="+searchWord+"&limit="+limit_value;
                    new ITunesAsyncTask(MainActivity.this).execute(url);
                    progressBar.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(MainActivity.this,"No Internet", Toast.LENGTH_LONG).show();
                }
            }
        });

        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(isConnected()) {
                    Album album = (Album) parent.getItemAtPosition(position);
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra(track_key, album);
                    startActivity(intent);
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        sortSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    sortType= "Date";
                }else
                {
                    sortType="Price";
                }
            }
        });
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }
    public void albumInfo(ArrayList<Album> list){
        if(list.size()>0) {
            trackArrayList = list;

            switch (sortType)
            {
                case "Price" :
                    Collections.sort(trackArrayList, new ComparePrice<Album>());
                    albumAdapter = new AlbumAdapter(MainActivity.this, R.layout.activity_track_details, list);
                    resultsListView.setAdapter(albumAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                    break;
                case "Date" :
                    Collections.sort(trackArrayList, new CompareDate<Album>());
                    albumAdapter = new AlbumAdapter(MainActivity.this, R.layout.activity_track_details, list);
                    resultsListView.setAdapter(albumAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                    break;
            }
        }
        else
        {
            Toast.makeText(this,"No Tracks found", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}
