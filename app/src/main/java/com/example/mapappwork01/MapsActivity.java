package com.example.mapappwork01;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<Restaurants> list;
    private GoogleMap mMap;
    Marker marker;
    Boolean don = true;
    Location location;
    FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            client = LocationServices.getFusedLocationProviderClient(MapsActivity.this);
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                }
            }
            client.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    if (locationResult != null) {
                        location = locationResult.getLastLocation();
                        if (location != null) {
                            mMap.addMarker(new MarkerOptions().title("My Location").position(new LatLng(location.getLatitude(), location.getLongitude())));
                            CameraPosition.Builder builder = new CameraPosition.Builder();
                            builder.target(new LatLng(location.getLatitude(), location.getLongitude()));
                            builder.zoom(15);
                            CameraPosition cameraPosition = builder.build();
                            CameraUpdate cameraUpdate =  CameraUpdateFactory.newCameraPosition(cameraPosition);
                            mMap.animateCamera(cameraUpdate , 2000 , null);
                        }
                    }
                }
            }, null);

        }
        Restaurants r1 = new Restaurants();
        r1.setName("مطعم معتوق");
        r1.setLatitude(31.51902);
        r1.setLongitude(34.445597);
        r1.setPhoto("http://www.matoug.com/restaurant/uploads/images/photos/RESTAURANT_2.png");
        r1.setDescription("المطعم الأول في قطاع غزة في تقديم جميع الوجبات الغربية و الشرقية و الوجبات السريعة و الحلويات > الجندي المجهول هاتف: 2826245- الرقم المجاني");

        Restaurants r2 = new Restaurants();
        r2.setName("مطعم التايلندي");
        r2.setLatitude(31.521967);
        r2.setLongitude(34.447872);
        r2.setPhoto("http://e-aswaq.com/images/10006/15178328_1049752961817218_8087366662049417775_n.jpg");
        r2.setDescription("مطعم التايلندي الشهير الأول في تحضير الشاورما في قطاع غزة");

        Restaurants r3 = new Restaurants();
        r3.setName("مطعم بالميرا");
        r3.setLatitude(31.521587);
        r3.setLongitude(34.447057);
        r3.setPhoto("https://mostaql.hsoubcdn.com/uploads/198455-1458154656-01-%D8%A7%D9%84%D8%B1%D8%A6%D9%8A%D8%B3%D9%8A%D8%A9-.png");
        r3.setDescription("من أقدم المطاعم في قطاع غزة");

        Restaurants r4 = new Restaurants();
        r4.setName("مترو مول");
        r4.setLatitude(31.517677);
        r4.setLongitude(34.444375);
        r4.setPhoto("https://i.ytimg.com/vi/7cSOfzNUt1U/maxresdefault.jpg");
        r4.setDescription("متعة التسوق في مترو مول");

        Restaurants r5 = new Restaurants();
        r5.setName("كابيتال مول");
        r5.setLatitude(31.515953);
        r5.setLongitude(34.449396);
        r5.setPhoto("https://cdnarabic1.img.sputniknews.com/images/102251/31/1022513162.jpg");
        r5.setDescription("أضخم مول في غزة يحتوى على محلات تجارية ومطاعم");

        Restaurants r6 = new Restaurants();
        r6.setName("Level Up");
        r6.setLatitude(31.511871);
        r6.setLongitude(34.448479);
        r6.setPhoto("https://static.timesofisrael.com/www/uploads/2015/04/gaza1-640x400.jpg");
        r6.setDescription("أعلى مطعم في غزة");


        list = new ArrayList<>();
        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        list.add(r6);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);

       if (don){
           for (int i = 0; i < list.size(); i++) {
               mMap.addMarker(new MarkerOptions().title(list.get(i).getName()).position(new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude())));
           }
       }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (int i = 0; i < list.size(); i++) {
                    Restaurants restaurants = list.get(i);
                    if (marker.getTitle().equals(list.get(i).getName())) {
                        InfoAdapter adapter = new InfoAdapter(MapsActivity.this, list , restaurants.getDescription() ,restaurants.getPhoto());
                        mMap.setInfoWindowAdapter(adapter);
                        marker.showInfoWindow();
                        break;
                    }
                }
                return false;
            }
        });
       don = false;
    }
}