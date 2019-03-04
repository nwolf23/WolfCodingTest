package com.cayuse.WolfCodingTest.services;

import com.google.maps.model.LatLng;

public interface GoogleApiServiceI {

    String getCity(String zipcode);

    String getTimeZone(LatLng latLng);

    Long getElevation(LatLng latLng);

    LatLng getLatLng(String zipcode);
}
