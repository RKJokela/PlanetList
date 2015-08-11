package com.rjokela.planetlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlanetListFragment extends Fragment {
    public final static String TAG = "PlanetListFragment";

    private final static String SELECTED_COLOR = "pref_selected_color";
    private final static String WHATIF_BTN_PREF = "pref_display_button";

    public static Integer mColorId;
    public static final int SHOW_PREFERENCES = 1;

    private final static String KEY_POSITION = "key_position";
    private final static String INITIAL_POSITION = "pref_position";

    private int mPosition;
    Planet[] planet_data;

    public PlanetListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet_list, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu() called.");
        Log.d(TAG, "Menu hashcode is" + Integer.toHexString(menu.hashCode()));
        // Inflate the menu; this adds items to the action bar if present
        try {
            inflater.inflate(R.menu.planet_fragment_items, menu);
        }
        catch (InflateException e) {
            Log.e(TAG, "onCreateOptionsMenu error - ", e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Log.d(TAG, "onOptionsItemSelected(R.id.action_about)");
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                startActivityForResult(new Intent(getActivity(),
                        PlanetPrefs.class), SHOW_PREFERENCES);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, mPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sp =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        mColorId = Integer.parseInt(sp.getString(SELECTED_COLOR, "0"));
        boolean showWhatIsButton = sp.getBoolean(WHATIF_BTN_PREF, true);

        planet_data = setupPlanets();

        if (savedInstanceState != null) {
            mPosition = savedInstanceState.getInt(KEY_POSITION, 0);
        } else {
            mPosition = sp.getInt(INITIAL_POSITION, 0);
            Log.d(TAG,"onActivityCreated mySharedPreferences mPosition " + mPosition);
        }

        Button whatButton = (Button) getActivity().findViewById(R.id.planetWhatIsItBtn);
        whatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickWhatIsItButton();
            }
        });

        ListView listView = (ListView) getActivity().findViewById(R.id.planetList);

        ImageTextArrayAdapter adapter = new ImageTextArrayAdapter(getActivity(),
                R.layout.planet_item, planet_data);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickListItem(view, position);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult - data: " + data);

        if (requestCode == SHOW_PREFERENCES) {
            SharedPreferences sp =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            mColorId = Integer.parseInt(sp.getString(SELECTED_COLOR, "0"));

            Log.d(TAG, "onActivityResult - color: " + mColorId);

            boolean showWhatIsButton = sp.getBoolean(WHATIF_BTN_PREF, true);

            Log.d(TAG, "onActivityResult - showWhatIsButton: " + showWhatIsButton);
        }
    }

    @Override
    public void onDestroy()
    {
        savePrefs(INITIAL_POSITION, mPosition);
        super.onDestroy();
    }

    private void savePrefs(String key, int value) {
        SharedPreferences sp =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(key, value);
        ed. apply ();
    }

    public void clickListItem(View view, int position){
        String item = ((TextView) view.findViewById(R.id.planet_name)).getText().toString();
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        mPosition = position;
    }

    private void clickWhatIsItButton() {
        String message = planet_data[mPosition].name + " "
                + getResources().getString(R.string.message_is_a) + " "
                + planet_data[mPosition].type;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private Planet[] setupPlanets() {
        String type_planet = getResources().getString(R.string.type_planet).toString();
        String type_minor_planet =
                getResources().getString(R.string.type_minor_planet).toString();
        return new Planet[] {
                new Planet(R.drawable.mercury_symbol,"Mercury", type_planet),
                new Planet(R.drawable.venus_symbol,"Venus", type_planet),
                new Planet(R.drawable.earth_symbol,"Earth", type_planet),
                new Planet(R.drawable.mars_symbol,"Mars", type_planet),
                new Planet(R.drawable.jupiter_symbol,"Jupiter", type_planet),
                new Planet(R.drawable.saturn_symbol,"Saturn", type_planet),
                new Planet(R.drawable.uranus_symbol,"Uranus", type_planet),
                new Planet(R.drawable.neptune_symbol,"Neptune", type_planet),
                new Planet(R.drawable.ceres_symbol,"Ceres", type_minor_planet),
                new Planet(R.drawable.pluto_symbol,"Pluto", type_minor_planet),
                new Planet(R.drawable.eris_symbol,"Eris", type_minor_planet),
        };
    }

}
