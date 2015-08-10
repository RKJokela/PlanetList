package com.rjokela.planetlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Randon K. Jokela on 8/9/2015.
 */
public class ImageTextArrayAdapter extends ArrayAdapter<Planet> {
    public final static String TAG = "ImageTextArrayAdapter";
    int layoutResourceId;
    Context context;
    private final Planet[] data;
    private LayoutInflater inflater;

    static class PlanetHolder {
        ImageView imgLogo;
        TextView txtName;
        TextView txtType;
    }

    public ImageTextArrayAdapter(Context context, int layoutResourceId, Planet[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlanetHolder holder = null;
        if (null == convertView) {
            Log.d(TAG, "getView: rowView null: position make new holder" + position);
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new PlanetHolder();
            holder.imgLogo = (ImageView)convertView.findViewById(R.id.planet_logo);
            holder.txtName = (TextView)convertView.findViewById(R.id.planet_name);
            holder.txtType = (TextView)convertView.findViewById(R.id.planet_type);
            // Tags can be used to store data within a view
            convertView.setTag(holder);
        }
        else {
            Log.d(TAG, "getView: rowView !null - reuse holder: position " + position);
            holder = (PlanetHolder)convertView.getTag();
        }
        // Display the information for that item
        Planet planet = data[position];
        holder.txtName.setText(planet.name);
        holder.txtType.setText(planet.type);
        holder.imgLogo.setImageResource(planet.logo);
        return convertView;
    }
}

