package com.example.locnt.app_project;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {

    private DrawerLayout mDrawerLayout;
    private GoogleMap mMap;
    double latitude;
    double longitude;
    private int PROXIMITY_RADIUS = 10000;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker, aMarker, bMarker, cMarker, dMarker, currentMarker, eMarker, fMarker, gMarker, hMarker;
    LocationRequest mLocationRequest;

    ImageView imgMenu, imgSearch;
    TextView txtDateMain, txtFragment, txtDirection;
    Spinner spinnerDistrict, spinnerType, spinnerHour;
    LinearLayout layoutFilter;
    Button btnFilter;
    Double distanceRoute = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
        checkLogin();
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgMenu = findViewById(R.id.img_menu);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        layoutFilter = findViewById(R.id.layout_filter);
        imgSearch = findViewById(R.id.img_search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutFilter.getVisibility() == View.GONE) {
                    layoutFilter.setVisibility(View.VISIBLE);
                } else {
                    layoutFilter.setVisibility(View.GONE);
                }
            }
        });

        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = (String) spinnerDistrict.getSelectedItem();
                if (item.equals("Vị trí hiện tại")) {
                    currentMarker.showInfoWindow();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(10.8534, 106.6293)));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                } else {
                    MarkerOptions marker1 = new MarkerOptions();
                    marker1.position(new LatLng(10.7994987, 106.6510931));
                    marker1.title("Sân Bóng Đá Lý Tự Trọng");
                    marker1.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_marker));

                    MarkerOptions marker2 = new MarkerOptions();
                    marker2.position(new LatLng(10.800363, 106.6442051));
                    marker2.title("Sân Bóng Chảo Lửa");
                    marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));

                    MarkerOptions marker3 = new MarkerOptions();
                    marker3.position(new LatLng(10.8084684, 106.6282646));
                    marker3.title("Sân bóng mini 20 Cộng Hòa");
                    marker3.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));

                    MarkerOptions marker4 = new MarkerOptions();
                    marker4.position(new LatLng(10.8112646, 106.6299058));
                    marker4.title("Sân bóng đá mini K334");
                    marker4.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));
                    eMarker = mMap.addMarker(marker1);
                    fMarker = mMap.addMarker(marker2);
                    gMarker = mMap.addMarker(marker3);
                    hMarker = mMap.addMarker(marker4);

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(10.8113, 106.6299)));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                }
            }
        });

//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setLogo(R.drawable.logo_new);
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        setNavigationViewListener();

        setSpinner();

        txtDateMain = findViewById(R.id.txt_date);
        txtDateMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        } else {
            Log.d("onCreate", "Google Play Services available.");
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_info: {
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_history: {
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_booking: {
                Intent intent = new Intent(this, BookingActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_logout: {
                logout();
                break;
            }
        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setSpinner() {
        spinnerDistrict = findViewById(R.id.spinner_district);
        String[] dataDistrict = {"Vị trí hiện tại", "Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8",
                "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Quận Thủ Đức", "Quận Bình Thạnh", "Quận Gò Vấp", "Quận Phú Nhuận",
                "Quận Tân Phú", "Quận Bình Tân", "Quận Tân Bình", "Huyện Nhà Bè", "Huyện Bình Chánh", "Huyện Hóc Môn", "Huyện Củ Chi", "Huyện Cần Giờ"};
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataDistrict);
        spinnerDistrict.setAdapter(adapterDistrict);
        popup(spinnerDistrict);

        spinnerType = findViewById(R.id.spinner_type);
        String[] dataType = {"Chọn loại sân", "Sân 5 người", "Sân 7 người", "Sân 11 người"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataType);
        spinnerType.setAdapter(adapterType);

        spinnerHour = findViewById(R.id.spinner_hour);
        String[] dataHour = {"6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00"};
        ArrayAdapter<String> adapterHour = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataHour);
        spinnerHour.setAdapter(adapterHour);
        popup(spinnerHour);
    }

    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtDateMain.setText(sdf.format(calendar.getTime()));
            }
        }, year, month, date);
        datePickerDialog.setCancelable(false);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    public void popup(Spinner s) {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(s);
            popupWindow.setHeight(500);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void addMarker() {
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(new LatLng(10.860665, 106.6263369));
        markerOptions1.title("Sân Bóng Sài Gòn FC Quận 12");
        markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(new LatLng(10.8552076, 106.6290468));
        markerOptions2.title("Sân bóng đá cỏ nhân tạo Đạt Đức");
        markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_marker));

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(new LatLng(10.8491383, 106.6285747));
        markerOptions3.title("Sân bóng đá cỏ nhân tạo Phương Nam");
        markerOptions3.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));

        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(new LatLng(10.8509507, 106.6270298));
        markerOptions4.title("Sân Bóng Trần Hưng Đạo");
        markerOptions4.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_marker));
        aMarker = mMap.addMarker(markerOptions1);
        bMarker = mMap.addMarker(markerOptions2);
        cMarker = mMap.addMarker(markerOptions3);
        dMarker = mMap.addMarker(markerOptions4);

//        if (mLastLocation != null) {
        MarkerOptions current = new MarkerOptions();
        current.position(new LatLng(10.852939, 106.629545));
        current.title("Vị trí hiện tại");
        current.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        currentMarker = mMap.addMarker(current);
        currentMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(10.852939, 106.629545)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
//        }
    }

    private void checkLogin() {
        SharedPreferences shared = getSharedPreferences("SportTalk", MODE_PRIVATE);
        boolean check = shared.getBoolean("checkLogin", false);
        if (!check) {
            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }

    private void logout() {
        SharedPreferences shared = getSharedPreferences("SportTalk", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.clear();
        editor.commit();

        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(1000);
//        mLocationRequest.setFastestInterval(1000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener) this);
//        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        //Place current location marker
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        Toast.makeText(this, "lat: " + location.getLatitude() + "long: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Vị trí hiện tại");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);
//
//
//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        addMarker();
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
//                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
            }
        } else {
            buildGoogleApiClient();
//            mMap.setMyLocationEnabled(true);
        }
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (!marker.getTitle().equals("Vị trí hiện tại")) {
            LatLng point = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            String url = getDirectionsUrl(currentMarker.getPosition(), point);
            mMap.clear();
            addMarker();
            DownloadDirectionData task = new DownloadDirectionData();
            task.execute(url);
            showBottomSheetDialog(marker.getTitle());
        }
        return true;
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        String key = "AIzaSyD6AKWLKmTVKe3bQYTU1Txl-baLpXrjhSg";
        String url = "https://maps.googleapis.com/maps/api/directions/json?" + parameters + "&key=" + key;

        return url;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        HttpURLConnection urlConnection = null;
        InputStream iStream = null;
        BufferedReader br = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            iStream = urlConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception download url", e.toString());
        } finally {
            if (br != null)
                br.close();
            if (iStream != null)
                iStream.close();
            if (urlConnection != null) ;
            urlConnection.disconnect();
        }
        return data;
    }

    private class DownloadDirectionData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                drawPolygon(jsonObject);
            } catch (JSONException e) {
                Log.d("Background Task", e.toString());
            }
        }
    }

    private void drawPolygon(JSONObject result) {
        JSONArray jRoute, jLeg, jStep;
        List<String> polylineList = new ArrayList<>();
        String distance = "";
        try {
            if (result.getString("status").equalsIgnoreCase("OK")) {
                jRoute = result.getJSONArray("routes");
                for (int i = 0; i < jRoute.length(); i++) {
                    jLeg = jRoute.getJSONObject(i).getJSONArray("legs");
                    for (int j = 0; j < jLeg.length(); j++) {
                        distance = jLeg.getJSONObject(j).getJSONObject("distance").getString("text");
                        jStep = jLeg.getJSONObject(j).getJSONArray("steps");
                        for (int k = 0; k < jStep.length(); k++) {
                            String polyline = jStep.getJSONObject(k).getJSONObject("polyline").getString("points");
                            polylineList.add(polyline);
                        }
                    }
                }
                for (int i = 0; i < polylineList.size(); i++) {
                    PolylineOptions options = new PolylineOptions();
                    options.color(Color.BLUE);
                    options.width(10);
                    options.addAll(PolyUtil.decode(polylineList.get(i)));
                    mMap.addPolyline(options);
                }

                Toast.makeText(getApplicationContext(), "Distance: " + distance.substring(0, distance.length() - 2).trim(), Toast.LENGTH_LONG).show();
                distanceRoute = Double.parseDouble(distance.substring(0, distance.length() - 2).trim());
            } else if (result.getString("status").equalsIgnoreCase("ZERO_RESULTS")) {
                Toast.makeText(getApplicationContext(), "No Result", Toast.LENGTH_SHORT).show();
            } else if (result.getString("status").equalsIgnoreCase("OVER_QUERY_LIMIT")) {
                Toast.makeText(getApplicationContext(), "Query Limit", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Log.e("Direction Error", e.getMessage());
        }
    }

    private double calculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return meter;
    }

    private void showBottomSheetDialog(String name) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bottomSheetFragment.setArguments(bundle);
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}
