package org.jinstagram.entity.locations;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;
import org.jinstagram.entity.common.Location;
import org.jinstagram.InstagramObject;

public class LocationSearchFeedgetLocationListTest {
    private LocationSearchFeed locationSearchFeed;
    private List<Location> expectedLocationList;

    @Before
    public void setUp() {
        locationSearchFeed = new LocationSearchFeed();
        expectedLocationList = new ArrayList<Location>();
        // Add some dummy locations to the list
        expectedLocationList.add(new Location());
        expectedLocationList.add(new Location());
        // Set the locationList field using reflection
        try {
            java.lang.reflect.Field field = LocationSearchFeed.class.getDeclaredField("locationList");
            field.setAccessible(true);
            field.set(locationSearchFeed, expectedLocationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLocationList_returnsExpectedList() {
        List<Location> actualLocationList = locationSearchFeed.getLocationList();
        Assert.assertEquals(expectedLocationList, actualLocationList);
    }
}
