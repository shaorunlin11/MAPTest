package org.jinstagram.entity.locations;

import java.util.ArrayList;
import java.util.List;
import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Location;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationSearchFeedsetLocationListTest {

    @Test
    public void testSetLocationList() throws Exception {
        LocationSearchFeed feed = new LocationSearchFeed();
        List<Location> locations = new ArrayList<Location>();
        locations.add(new Location());

        feed.setLocationList(locations);

        Field field = LocationSearchFeed.class.getDeclaredField("locationList");
        field.setAccessible(true);
        List<Location> result = (List<Location>) field.get(feed);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testSetLocationListWithNull() throws Exception {
        LocationSearchFeed feed = new LocationSearchFeed();
        feed.setLocationList(null);

        Field field = LocationSearchFeed.class.getDeclaredField("locationList");
        field.setAccessible(true);
        List<Location> result = (List<Location>) field.get(feed);

        assertNull(result);
    }
}
