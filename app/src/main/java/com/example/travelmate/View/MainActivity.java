package com.example.travelmate.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelmate.Adapter.CustomAdapter;
import com.example.travelmate.Adapter.RecyclerItemClickListener;
import com.example.travelmate.Model.DataModel;
import com.example.travelmate.Model.DataResponse;
import com.example.travelmate.Network.GetDataService;
import com.example.travelmate.Network.RetrofitClientInstance;
import com.example.travelmate.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

     CustomAdapter adapter;
     RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    boolean isPlay = false;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView) findViewById(R.id.titlenew);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        if (!isInternetOn()) {
          //  Log.e("Network Check", "" + "Please check your internet connectivity.");
            Toast.makeText(MainActivity.this,
                    "Network Check : Please check your internet connectivity",
                    Toast.LENGTH_LONG).show();
            return;
        } else {
            /*Create handle for the RetrofitInstance interface*/
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

            Call<DataResponse> call = service.getAllPhotos();
            call.enqueue(new Callback<DataResponse>() {

                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    progressDoalog.dismiss();
                    //  Log.e("Reall Data",""+response.body().getCustName());
                    generateDataList(response.body());
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    progressDoalog.dismiss();
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(DataResponse photoList) {
        recyclerView = (RecyclerView) findViewById(R.id.customRecyclerView);
        title.setText("  Hi "+photoList.getCustName()+",");

        adapter = new CustomAdapter(this,photoList.getResults(),recyclerItemClickListener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void selectedView(View view) {

        if(isPlay){
            view.setBackgroundResource(R.drawable.outheart);
        }else{
            view.setBackgroundResource(R.drawable.red);
        }

        isPlay = !isPlay;
    }

    //Custom RecyclerView Listener
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(DataModel mov) {

            Intent intent=new Intent(getApplicationContext(),DetailedActivity.class);
            intent.putExtra("place",mov.getPlace());
            intent.putExtra("url",mov.getUrl());
            intent.putExtra("date",mov.getDate());
            intent.putExtra("desc",mov.getDesc());
            intent.putExtra("rate",mov.getRate());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
            Toast.makeText(MainActivity.this,
                    "List title:  " + mov.getPlace(),
                    Toast.LENGTH_LONG).show();

        }
    };

    //Internet Check
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

