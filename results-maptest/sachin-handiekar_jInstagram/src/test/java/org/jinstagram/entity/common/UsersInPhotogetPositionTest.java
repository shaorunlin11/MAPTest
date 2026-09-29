package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class UsersInPhotogetPositionTest {

    @Test
    public void testGetPosition() throws Exception {
        // Arrange
        UsersInPhoto usersInPhoto = new UsersInPhoto();
        GridPosition expectedPosition = new GridPosition();

        // Set the position field using reflection to bypass access modifiers
        java.lang.reflect.Field positionField = UsersInPhoto.class.getDeclaredField("position");
        positionField.setAccessible(true);
        positionField.set(usersInPhoto, expectedPosition);

        // Act
        GridPosition actualPosition = usersInPhoto.getPosition();

        // Assert
        Assert.assertEquals(expectedPosition, actualPosition);
    }
}
