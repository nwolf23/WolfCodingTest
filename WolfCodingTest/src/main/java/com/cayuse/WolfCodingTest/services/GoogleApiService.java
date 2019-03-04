package com.cayuse.WolfCodingTest.services;

import com.cayuse.WolfCodingTest.configuration.GoogleApiConfiguration;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.TimeZone;

@Service
public class GoogleApiService implements GoogleApiServiceI {

    private GoogleApiConfiguration googleApiConfiguration;

    private GeoApiContext context;

    public GoogleApiService(GoogleApiConfiguration config) {
        googleApiConfiguration = config;
        this.context = new GeoApiContext.Builder().apiKey(googleApiConfiguration.getApiKey()).build();
    }


    @Override
    public String getCity(String zipcode) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context,zipcode).await();
            if ( results != null && results.length > 0) {
                return getCityFromAddressComponents(results[0].addressComponents);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTimeZone(LatLng latLng) {
        try {
            TimeZone result = TimeZoneApi.getTimeZone(context, latLng).await();
            return result.getDisplayName();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return null;
    }

    @Override
    public Long getElevation(LatLng latLng) {
        try {
            ElevationResult result =  ElevationApi.getByPoint(context, latLng).await();
            return getElevationInFeet(result.elevation);
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public LatLng getLatLng(String zipcode) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context,zipcode).await();
            if ( results != null && results.length > 0) {
                return results[0].geometry.location;
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }

    public String getCityFromAddressComponents(AddressComponent[] addressComponents) {
        AddressComponent filterAddressComponent = Arrays.asList(addressComponents).stream()
                .filter(ac -> Arrays.asList(ac.types).contains(AddressComponentType.LOCALITY))
                .findAny()
                .orElse(null);
        return filterAddressComponent != null ? filterAddressComponent.shortName : "";
    }

    public Long getElevationInFeet(Double elevation) {
        return Math.round(elevation / 0.3048);
    }
}
