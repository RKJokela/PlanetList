package com.rjokela.planetlist;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Randon K. Jokela on 8/10/2015.
 */
public class PlanetPrefs extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
