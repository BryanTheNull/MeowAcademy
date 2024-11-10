package com.example.appmeowacademy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.appmeowacademy.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    private MyLocationNewOverlay myLocationOverlay;
    private double currentLatitud, currentLongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Cargar la configuración de OpenStreetMap
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        // Inicializar el MapView
        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        // Coordenadas de la oficina MeowCorp
        double MeowLatitud = -33.4176557;
        double MeowLongitud = -70.5320263;

        // Punto MeowCorp
        GeoPoint MeowPoint = new GeoPoint(MeowLatitud, MeowLongitud);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(MeowPoint);

        // Crear un marcador para MeowCorp
        Marker marcadorMeowCorp = new Marker(mapView);
        marcadorMeowCorp.setPosition(MeowPoint);
        marcadorMeowCorp.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorMeowCorp.setTitle("MeowCorp");
        marcadorMeowCorp.setSnippet("Oficina MeowCorp");
        mapView.getOverlays().add(marcadorMeowCorp);

        // Verificamos permisos de la ubicacion
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permisos si no están otorgados
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        // Añadir el overlay para mostrar la ubicación actual
        myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), mapView);
        myLocationOverlay.enableMyLocation();
        mapView.getOverlays().add(myLocationOverlay);

        // Actualizar la ubicación en el mapa cuando esté disponible
        myLocationOverlay.runOnFirstFix(new Runnable() {
            @Override
            public void run() {
                // Obtener la latitud y longitud actuales
                currentLatitud = myLocationOverlay.getMyLocation().getLatitude();
                currentLongitud = myLocationOverlay.getMyLocation().getLongitude();

                // Ubicación actual
                GeoPoint actualPoint = new GeoPoint(currentLatitud, currentLongitud);

                //Marcador ubicación actual
                Marker marcadorActual = new Marker(mapView);
                marcadorActual.setPosition(actualPoint);
                marcadorActual.setAnchor(0.2f, 0.4f);
                marcadorActual.setInfoWindowAnchor(0.2f, 0.0f);
                marcadorActual.setTitle("TÚ");
                marcadorActual.setSnippet("Ubicación Actual");

                mapView.getOverlays().add(marcadorActual);

                //Agregamos una linea entre ambos puntos
                Polyline linea = new Polyline();
                linea.addPoint(MeowPoint);
                linea.addPoint(actualPoint);
                linea.setColor(0xFF0000FF);
                linea.setWidth(5);
                mapView.getOverlayManager().add(linea);


            }

       });




    }
    public void onClickProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}