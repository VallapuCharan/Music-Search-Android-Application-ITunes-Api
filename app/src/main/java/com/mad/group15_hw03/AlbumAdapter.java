/*
Assignment : HomeWork 03
File Name : Group15_HW03.zip
Full Names : Manideep Reddy Nukala, Sai Charan Reddy Vallapureddy
 */
package com.mad.group15_hw03;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AlbumAdapter extends ArrayAdapter<Album> {
    public AlbumAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Album> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Album album = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_track_details, parent, false);
        }
        TextView track =  convertView.findViewById(R.id.track);
        TextView price =  convertView.findViewById(R.id.price);
        TextView artist =  convertView.findViewById(R.id.artist);
        TextView date =  convertView.findViewById(R.id.date);
        track.setText(album.trackName);
        price.setText(String.valueOf(album.trackPrice)+" $");
        artist.setText(album.artistName);
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date1 = null;
        try {
            date1 = sdfSource.parse(album.releaseDate);
            SimpleDateFormat str = new SimpleDateFormat("MM-dd-yyyy");
            String newdate = str.format(date1);
            album.releaseDate = newdate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        date.setText(album.releaseDate);
        return convertView;
    }
}
