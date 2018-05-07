package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JurigXiaomi on 4/18/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
    public Context c;

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Store Earthquake object in var objectEarthquake
        // this.getItem(position) is method form class ArrayAdapter
        Earthquake objectEarthquake = this.getItem(position);

        // Store object TextView in var magTextView
        // Create magnitudeFormat using call formatMagnitude method
        // SetText String magnitudeFormat to TextView
        TextView magTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        String magnitudeFormat = this.formatMagnitude(objectEarthquake.getMagnitude());
        magTextView.setText(magnitudeFormat);

        // Store object GradientDrawable in var magnitudeCircle
        // Set color magnitude use getMagnitudeColor method
        // Set color magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        Integer magnitudeColor = this.getMagnitudeColor(objectEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // Create var originalLocation for store original name location
        // Create var primaryLocation for store name primaryLocation
        // Create var locationOffset for store name locationOffset
        String originalLocation = objectEarthquake.getPlace();
        String primaryLocation;
        String locationOffset;

        // Check if in String originalLocation contains have keyword " of " then
        // Execute to split originalLocation
        // else locationOffset use " Near the " keyword and
        // primaryLocation originalLocation name
        if (originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        // Store TextView Object in var locationPrimaryTextView then
        // setText for TextView Object to id location_primary
        TextView locationPrimaryTextView = (TextView) listItemView.findViewById(R.id.location_primary);
        locationPrimaryTextView.setText(primaryLocation);

        // Store TextView Object in var locationOffsetTextVIew then
        // setText for TextView Object to id location_offset
        TextView locationOffsetTextVIew = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetTextVIew.setText(locationOffset);

        // Create Object Date, store in var dateObject
        Date dateObject = new Date(objectEarthquake.getTime());

        // Store TextView in var dateView thhen
        // Call method this.formatDate() to format date
        // setText for TextView Object id date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = this.formatDate(dateObject);
        dateView.setText(formattedDate);

        // Store TextView in var timeView thhen
        // Call method this.formatTime() to format timeView
        // setText for TextView Object id time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = this.formatTime(dateObject);
        timeView.setText(formattedTime);

        // ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // iconView.setImageResource(objectEarthquake.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Method for formatDate use SimpleDateFormat object
     *
     * @param dateObject is object Date look line 100
     */
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Method for formatDate use SimpleDateFormat object
     *
     * @param dateObject is object Date look line 100
     */
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Method for formatMagnitude value, use DecimalFormat object
     *
     * @param magnitude is value from object Earthquake.getMagnitude()
     */
    private String formatMagnitude(Double magnitude){
        DecimalFormat doubleFormat = new DecimalFormat("0.0");
        return doubleFormat.format(magnitude);
    }

    private Integer getMagnitudeColor(Double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
