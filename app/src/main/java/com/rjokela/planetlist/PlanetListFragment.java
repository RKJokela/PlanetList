package com.rjokela.planetlist;

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

    private int mPosition;
    Planet[] planet_data;

    public PlanetListFragment() {
    }

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
            case R.id.whatIsIt:
                Log.d(TAG, "onOptionsItemSelected(R.id.whatIsIt)");
                clickWhatIsItButton();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        planet_data = setupPlanets();

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
