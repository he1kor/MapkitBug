package com.example.mapkitbug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.location.FilteringMode;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationManager;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.mapview.MapView;

public class MainActivity extends AppCompatActivity{

    MapKit mapKit;
    MapView mapView;
    LocationListener locationListener;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String MAPKIT_KEY = "key";
        MapKitFactory.setApiKey(MAPKIT_KEY);
        MapKitFactory.initialize(this);
        mapKit = MapKitFactory.getInstance();

        setContentView(R.layout.activity_main);
        mapView = this.findViewById(R.id.map);

        mapView.setVisibility(View.VISIBLE);

        locationManager = mapKit.createLocationManager();
        double DESIRED_ACCURACY = 0;
        long MINIMAL_TIME = 1;
        double MINIMAL_DISTANCE = 0;
        boolean USE_IN_BACKGROUND = false;
        locationListener = new LocationListener() {
            @Override
            public void onLocationUpdated(@NonNull Location location) {
                System.out.println("LOCATION UPDATED");
            }

            @Override
            public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {
                System.out.println("STATUS UPDATED");
            }
        };
        locationManager.subscribeForLocationUpdates(DESIRED_ACCURACY, MINIMAL_TIME,
                MINIMAL_DISTANCE, USE_IN_BACKGROUND, FilteringMode.OFF, locationListener);
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        mapKit.onStop();
        super.onStop();
    }
    @Override
    protected void onStart() {
        super.onStart();
        mapKit.onStart();
        mapView.onStart();
    }
}