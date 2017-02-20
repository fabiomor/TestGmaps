package com.fabio.testing.testgmaps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fabiomoretti on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapsFragment mapsFragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, mapsFragment, mapsFragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
