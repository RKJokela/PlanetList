package com.rjokela.planetlist;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlanetListFragment extends Fragment {

    public PlanetListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet_list, container, false);
    }

    public void doClick(View view){
        Toast.makeText(getActivity(),
                ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    }
}
