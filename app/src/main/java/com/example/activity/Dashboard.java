package com.example.activity;

import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Dashboard extends AppCompatActivity {

    EditText city_input;
    LinearLayout result;
    private FusedLocationProviderClient fusedLocationClient;
    Context c = this;
    String comb = "", comb2 = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //REMOVE TITLEBAR
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();

        VideoView videoview = (VideoView) findViewById(R.id.introbg);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rain);
        videoview.setDrawingCacheEnabled(true);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int videoWidth = mp.getVideoWidth();
                int videoHeight = mp.getVideoHeight();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenWidth = displayMetrics.widthPixels;
                int screenHeight = displayMetrics.heightPixels;

                float scaleY = 1.0f;
                float scaleX = (videoWidth * screenHeight / videoHeight) / screenWidth;

                int pivotPointX = (int) (screenWidth / 2);
                int pivotPointY = (int) (screenHeight / 2);

                videoview.setScaleX(scaleX);
                videoview.setScaleY(scaleY);
                videoview.setPivotX(pivotPointX);
                videoview.setPivotY(pivotPointY);

                mp.setLooping(true);
                mp.start();
            }
        });


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        //REMOVE STATUSBAR TO MAKE IT FULLSCREEN :)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


    }

    double lati = 0f;
    double longi = 0f;

    private void fetchLastLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
//                    Toast.makeText(MainActivity.this, "Permission not granted, Kindly allow permission", Toast.LENGTH_LONG).show();
                showPermissionAlert();
                return;
            }


        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object

                            Geocoder geocoder = new Geocoder(c, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            lati = location.getLatitude();
                            longi = location.getLongitude();

                            String url = "https://api.sehavniva.no/tideapi.php?tide_request=locationdata&lat=" + lati + "&lon=" + longi + "&datatype=PRE&lang=nl&place=Gol&dst=1&refcode=CD&fromtime=2022-10-18T09%3A00&totime=2022-10-18T11%3A00&interval=10";

                            //JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            //creating a string request to send request to the url
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {

                                        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                                        Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(response.getBytes()));

                                        for (int i = 0; i < parse.getElementsByTagName("waterlevel").getLength(); i++) {
                                            String user_id = parse.getElementsByTagName("waterlevel").item(i).getAttributes().getNamedItem("value").getNodeValue();
                                            Toast.makeText(getApplicationContext(), user_id, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            //displaying the error in toast if occurrs
                                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            //creating a request queue
                            RequestQueue requestQueue = Volley.newRequestQueue(c);

                            //adding the string request to request queue
                            requestQueue.add(stringRequest);

                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();

                            comb = city + ", " + state + ", " + country;
                            comb2 = city + ", " + country;
                            Toast.makeText(getApplicationContext(), "Detected location: " + comb, Toast.LENGTH_SHORT).show();

                        }
                        fetch_weather(null);
                        return;
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 123: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    // permission was denied, show alert to explain permission
                    showPermissionAlert();
                } else {
                    //permission is granted now start a background service
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        fetchLastLocation();
                    }
                }
            }
        }
    }

    private void showPermissionAlert() {
        if (ActivityCompat.checkSelfPermission(Dashboard.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(Dashboard.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Dashboard.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
    }


    public void fetch_weather(View view) {
        city_input = findViewById(R.id.input_city);
        String t = "";

            t = comb;

            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + t + "&appid=e6fa708b3cb2a7df333b674e1554880c";
        Log.d("tag",url);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {

                        //SHOW

                        TextView humidity = findViewById(R.id.humidity);
                        TextView wind = findViewById(R.id.wind);
                        TextView temp = findViewById(R.id.temperature);
                        TextView cloud_title = findViewById(R.id.cloud_title);
                        TextView cloud_description = findViewById(R.id.cloud_description);
                        TextView country = findViewById(R.id.country);
                        TextView title = findViewById(R.id.title);
                        TextView status = findViewById(R.id.status);
                        TextView description = findViewById(R.id.statusdesc);
                        TextView extra = findViewById(R.id.statusdescex);

                        //WEATHER AND DESCRIPTION
                        JSONArray weatherinfo = response.getJSONArray("weather");
                        JSONObject weather_s = weatherinfo.getJSONObject(0);
                        cloud_title.setText(weather_s.getString("main"));
                        cloud_description.setText(weather_s.getString("description"));
                        title.setText(comb2);

                        JSONObject res = response.getJSONObject("main");
                        //TEMPERATURE
                        temp.setText(String.format("%.0f", (Double.parseDouble(res.getString("temp")) - 273.15)) + "°");

                        //HUMIDITY
                        humidity.setText(res.getString("humidity"));

                        //COUNTRY
                        country.setText(response.getJSONObject("sys").getString("country"));

                        //CHANGE WEATHER ICON DEPENDING ON CLOUD PERCENTAGE/CHANGED TO DESCRIPTION
                        ImageView status_icon = findViewById(R.id.statusicon);
                        if(weather_s.getString("main") == "Clouds") {
                            status_icon.setImageResource(R.drawable.sunny);
                            status.setText("No need");
                            description.setText("You are safe");
                            extra.setText("It's sunny!");
                        }   else if(weather_s.getString("main") == "Thunderstorm" || weather_s.getString("main") == "Rain" || weather_s.getString("main") == "Drizzle") {
                            status_icon.setImageResource(R.drawable.rain);
                            status.setText("Bring one");
                            description.setText("Rain imminent");
                            extra.setText("It might rain!");
                        }   else if(weather_s.getString("description") == "few clouds" || weather_s.getString("description") == "scattered clouds" || weather_s.getString("description") == "broken clouds") {
                            status_icon.setImageResource(R.drawable.cloudsunny);
                            status.setText("Consider one");
                            description.setText("Kinda safe");
                            extra.setText("Bring one if you can.");
                        }   else {
                            status_icon.setImageResource(R.drawable.cloudsunny);
                            status.setText("Consider one");
                            description.setText("Kinda safe");
                            extra.setText("Bring one if you can.");
                        }

                        //UNNECESSARY. WIND DIRECTION.
                        Double degrees = Double.parseDouble(response.getJSONObject("wind").getString("deg"));
                        String direction = "N";
                        if (degrees >= 0 && degrees < 90) direction = "E";
                        else if (degrees >= 90 && degrees < 180) direction = "N";
                        else if (degrees >= 180 && degrees < 270) direction = "W";
                        else if (degrees >= 270 && degrees < 360) direction = "S";
                        else if (degrees >= 360) direction = "E";

                        wind.setText(response.getJSONObject("wind").getString("speed") + " " + direction);

                    } catch (JSONException error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        }




    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent toLogin = new Intent(this, MainActivity.class);
        startActivity(toLogin);
    }
}