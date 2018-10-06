package com.example.locnt.app_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationFragment extends Fragment implements OnMapReadyCallback{
    private MapView mapView;
    private GoogleMap gMap;

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

//        mMapView = (MapView) view.findViewById(R.id.mapView);
//
//        // inflat and return the layout
//        mMapView.onCreate(savedInstanceState);
//        mMapView.onResume();// needed to get the map to display immediately
//
//        MapsInitializer.initialize(getActivity());
//        GoogleMap googleMap = mMapView.getMapAsync();
//        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//        mapView.onCreate(savedInstanceState);
//        mapView.onResume();
//        mapView.getMapAsync(this);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mapView)
            mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mapView)
            mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mapView)
            mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != mapView)
            mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (null != mapView)
            mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng sydney = new LatLng(34.0052982, -83.9888499);
        gMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
