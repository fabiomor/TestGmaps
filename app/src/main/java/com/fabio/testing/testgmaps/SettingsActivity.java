package com.fabio.testing.testgmaps;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import java.util.Arrays;
import java.util.Map;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            Map<String, ?> preferencesValues = getPreferenceScreen().getSharedPreferences().getAll();
            for (String key : preferencesValues.keySet()) {
                setSummary(key, preferencesValues.get(key));
            }
        }

        private void setSummary(String key, Object value) {
            Preference preference = findPreference(key);
            if (preference == null) {
                return;
            }
            String valueStr = String.valueOf(value);
            if (key.equals(Constants.PASSWORD_KEY)) {
                char[] hiddenPassword = new char[valueStr.length()];
                for (int i=0; i<hiddenPassword.length; i++) {
                    hiddenPassword[i] = '*';
                }
                preference.setSummary(new String(hiddenPassword));
                return;
            }
            preference.setSummary(valueStr);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            setSummary(key, sharedPreferences.getString(key, ""));
        }
    }
}
