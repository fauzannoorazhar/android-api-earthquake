package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JurigXiaomi on 4/18/2018.
 */

public class Earthquake {
    private Double mMagnitude;
    private String mPlace;
    private Long mTime;
    private String mUrl;

    public Earthquake(Double magnitude, String place, Long time, String url){
        this.mMagnitude = magnitude;
        this.mPlace = place;
        this.mTime = time;
        this.mUrl = url;
    }

    public Double getMagnitude(){
        return this.mMagnitude;
    }

    public String getPlace(){
        return this.mPlace;
    }

    public Long getTime(){
        return this.mTime;
    }

    public String getUrl() {
        return this.mUrl;
    }

    /*public Date getDateConvert() {
        Long timeStamp = mTime;
        Date dateObject = new Date(timeStamp);

        SimpleDateFormat dateFormatterObject = new SimpleDateFormat("MMM DD, yyyy");
        Date dataToDisplay = dateFormatterObject.format(dateObject);

        return dataToDisplay;
    }*/
}
