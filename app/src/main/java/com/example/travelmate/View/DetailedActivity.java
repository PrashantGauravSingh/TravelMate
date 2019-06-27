package com.example.travelmate.View;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelmate.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {
    TextView place,date,rating,description;
    ImageView imgPlace;
    String places;
    String desc;
    String dates;
    String rate;
    String url;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgPlace= (ImageView) findViewById(R.id.img_thumbnail);
        date= (TextView) findViewById(R.id.date);
        description= (TextView) findViewById(R.id.movieDesc);

        Intent intent=getIntent();
        if(intent.hasExtra("place")){

            places=getIntent().getExtras().getString("place");
            desc=getIntent().getExtras().getString("desc");
            dates=getIntent().getExtras().getString("date");
            rate=getIntent().getExtras().getString("rate");
            url=getIntent().getExtras().getString("url");

            if(!isInternetOn()) {
                Log.e("Network Check",""+"Please check your internet connectivity.");
                return;
            }else{
                getSupportActionBar().setTitle(places);
                Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
                builder.downloader(new OkHttp3Downloader(getApplicationContext()));
                builder.build().load(url)
                        .placeholder((R.drawable.ic_launcher_background))
                        .error(R.drawable.ic_launcher_background)
                        .into(imgPlace);
                description.setText(desc);
                date.setText(dates);
            }

        }
    }
    public  boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            return false;
        }
        return false;
    }

}
