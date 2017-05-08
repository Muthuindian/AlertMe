package com.tech42labs.alertme.model;

import io.realm.RealmObject;

/**
 * Created by mari on 06/05/17.
 */

public class Location extends RealmObject {

    private long latitude;

    private long longitude;

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
