package com.fabio.testing.testgmaps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity implements MapsFragment.OnLocationChangedListener, View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();


    private SharedPreferences sharedPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;

    private Button button;
    private MapsFragment mapsFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapsFragment = new MapsFragment();
        //getSupportFragmentManager().beginTransaction()
                //.replace(R.id.activity_main, mapsFragment, mapsFragment.getClass().getSimpleName()).addToBackStack(null).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mapscontainer,mapsFragment)
                .commit();
        
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferenceChangeListener = new OnSharedPreferenceChangeListener();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        super.onStop();
        if (sharedPreferenceChangeListener != null) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(LatLng latLng) {
        Log.i(TAG,"latitude: " + latLng.latitude + " longitude: " + latLng.longitude);
    }

    @Override
    public void onClick(View v) {
        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mapsFragment.placeMarker();
            }
        });
    }

    // menu

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void updateSettingsFromPreferences(String key) {
        switch (key) {
            case Constants.USERNAME_KEY:
                Settings.setUsername(sharedPreferences.getString(key, "username"));
                break;

            case Constants.PASSWORD_KEY:
                Settings.setPassword(sharedPreferences.getString(key, "password"));
                break;

            case Constants.SERVER_PROTOCOL_KEY:
                Settings.setServerProtocol(sharedPreferences.getString(key, "http"));
                break;

            case Constants.SERVER_ADDRESS_KEY:
                Settings.setServerAddress(sharedPreferences.getString(key, "localhost"));
                break;

            case Constants.SERVER_PORT_KEY:
                Settings.setServerPort(Integer.parseInt(sharedPreferences.getString(key, "8080")));
                break;

            case Constants.ZOOM_LEVEL:
                Settings.setZoomLevel(Float.parseFloat(sharedPreferences.getString(key, "16")));
                break;

            default:
        }
    }
    
    //listeners

    private class OnSharedPreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            updateSettingsFromPreferences(key);
        }
    }
}
