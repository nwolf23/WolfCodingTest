package com.cayuse.WolfCodingTest.services;

import com.cayuse.WolfCodingTest.configuration.GoogleApiConfiguration;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class GoogleApiServiceTests {

    @Mock
    private GoogleApiConfiguration mockConfig;

    @InjectMocks
    private GoogleApiService googleApiService;

    @Before
    public void setup() {
        mockConfig = mock(GoogleApiConfiguration.class);
        googleApiService = new GoogleApiService(mockConfig);
    }

    @Test
    public void getCityFromAddressComponents_returns_expected_city() throws IOException {
        String result = googleApiService.getCityFromAddressComponents(buildAddressComponents());
        Assert.assertEquals("Bend", result);
    }

    @Test
    public void getElevationInFeet_returns_expected_calculated_value(){
        Double elevation = Double.valueOf(1035.647338867188);
        Long result = googleApiService.getElevationInFeet(elevation);
        Assert.assertEquals(Long.valueOf(3398), result);
    }

    private AddressComponent[] buildAddressComponents() throws IOException {
        AddressComponent a1 = new AddressComponent();
        a1.shortName = "97702";
        a1.longName = "97702";
        AddressComponentType[] a1t = {AddressComponentType.POSTAL_CODE};
        a1.types = a1t;
        AddressComponent a2 = new AddressComponent();
        a2.shortName = "Bend";
        a2.longName = "Bend";
        AddressComponentType[] a2t = {AddressComponentType.LOCALITY, AddressComponentType.POLITICAL};
        a2.types = a2t;
        AddressComponent[] addressComponents = {a1, a2};
       return addressComponents;
    }
}