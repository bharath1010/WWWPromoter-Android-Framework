package com.falconnect.wwwpromoters_sample;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.falconnect.wwwpromoterslib.HandleXML;
import com.falconnect.wwwpromoterslib.PromotersConnection;


public class MainActivity extends AppCompatActivity {
    //Url
    public String finalUrl;

    //Lib Files
    public HandleXML obj;
    public PromotersConnection getConnection;

    //Buttons
    Button more_games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lib File Initilize
        getConnection = new PromotersConnection();
        finalUrl = getConnection.GetConnection(this);

        //Button Initilize
        more_games = (Button) findViewById(R.id.more_games);

        //Button OnClick Listener
        more_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If Internet Available
                if (isNetworkAvailable()) {
                    obj = new HandleXML(finalUrl);
                    obj.fetchXML();
                    while (obj.parsingComplete) ;
                    String url = obj.getDescription();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
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

