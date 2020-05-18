package com.example.assignment2_exercise3_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private Button BT;
    private TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BT = findViewById(R.id.BT);
        TV = findViewById(R.id.textView);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            return;
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            Log.i("log", String.valueOf(location.getLongitude()));
                            Log.i("lat", String.valueOf(location.getLatitude()));
                            TV.setText("Longitude:" + String.valueOf(location.getLongitude()) + "; "+"Latitude:"+String.valueOf(location.getLatitude()));
                        }
                    }
                });

            }
        });



    }
}
