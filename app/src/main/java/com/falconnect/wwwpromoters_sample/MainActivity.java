package com.falconnect.wwwpromoters_sample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.falconnect.wwwpromoterslib.PromotersConnection;


public class MainActivity extends AppCompatActivity {
    //Lib Files
    public PromotersConnection getConnection;

    //Buttons
    Button more_games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lib File Initilize
        getConnection = new PromotersConnection();

        //Button Initilize
        more_games = (Button) findViewById(R.id.more_games);

        //Button OnClick Listener
        more_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If Internet Available
                if (isNetworkAvailable()) {
                    getConnection.GetConnection(MainActivity.this);
                }
                //If Internet not Available
                else {
                    Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Check Internet Connection!!!
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

