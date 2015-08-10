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

    public ImageTextArrayAdapter(Context context, int layoutResourceId, Planet[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(TAG, "getView: rowView null: position " + position);
        View rowView = inflater.inflate(layoutResourceId, parent, false);
        TextView planetNameView = (TextView) rowView.findViewById(R.id.planet_name);
        TextView planetTypeView = (TextView) rowView.findViewById(R.id.planet_type);
        ImageView planetLogoView = (ImageView) rowView.findViewById(R.id.planet_logo);
        planetNameView.setText(data[position].name);
        planetTypeView.setText(data[position].type);
        planetLogoView.setImageResource(data[position].logo);
        return rowView;
    }
}

