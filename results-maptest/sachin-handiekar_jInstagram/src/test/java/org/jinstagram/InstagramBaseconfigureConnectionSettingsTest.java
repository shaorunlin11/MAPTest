package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.*;

import org.jinstagram.http.Request;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class InstagramBaseconfigureConnectionSettingsTest {

    @Test
    public void testConfigureConnectionSettings() throws Exception {
        // Arrange
        Request request = mock(Request.class);
        InstagramConfig config = mock(InstagramConfig.class);

        when(config.getConnectionTimeoutMills()).thenReturn(5000);
        when(config.getReadTimeoutMills()).thenReturn(10000);
        when(config.isConnectionKeepAlive()).thenReturn(true);

        // Act
        InstagramBase.configureConnectionSettings(request, config);

        // Assert
        verify(request).setConnectTimeout(5000, TimeUnit.MILLISECONDS);
        verify(request).setReadTimeout(10000, TimeUnit.MILLISECONDS);
        verify(request).setConnectionKeepAlive(true);
    }
}
