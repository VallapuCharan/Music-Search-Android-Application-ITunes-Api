/*
Assignment : HomeWork 03
File Name : Group15_HW03.zip
Full Names : Manideep Reddy Nukala, Sai Charan Reddy Vallapureddy
 */
package com.mad.group15_hw03;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ITunesAsyncTask extends AsyncTask<String,Void, ArrayList> {
    MainActivity mainActivity;

    public ITunesAsyncTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected ArrayList doInBackground(String... strings) {
        HttpURLConnection connection = null;
        URL url = null;
        ArrayList<Album> result = new ArrayList<Album>();
        try {
            url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
               String Json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                JSONObject root = new JSONObject(Json);
                JSONArray track_details = root.getJSONArray("results");
                for (int i = 0; i < track_details.length(); i++) {
                    JSONObject artistJson = track_details.getJSONObject(i);
                    Album album = new Album();
                    album.trackName = artistJson.getString("trackName");
                    album.primaryGenreName = artistJson.getString("primaryGenreName");
                    album.artistName = artistJson.getString("artistName");
                    album.collectionName = artistJson.getString("collectionName");
                    album.trackPrice = artistJson.getString("trackPrice");
                    album.collectionPrice = artistJson.getString("collectionPrice");
                    album.artworkUrl100 = artistJson.getString("artworkUrl100");
                    album.releaseDate = artistJson.getString("releaseDate");
                    result.add(album);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            Log.d("exception","No value");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        mainActivity.albumInfo(arrayList);
    }
}
