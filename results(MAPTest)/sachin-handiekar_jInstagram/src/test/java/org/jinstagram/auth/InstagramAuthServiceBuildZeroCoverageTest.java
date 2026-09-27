package org.jinstagram.auth;

import org.junit.Test;
import java.net.Proxy;

public class InstagramAuthServiceBuildZeroCoverageTest {
    @Test
    public void testBuildWithNonEmptyApiKeys() {
        InstagramAuthService auth = new InstagramAuthService();
        auth.apiKey("testApiKey");
        auth.apiSecret("testApiSecret");
        auth.callback("http://example.com/callback");
        auth.scope("public_content");
        auth.display("page");
        auth.proxy(Proxy.NO_PROXY);

        auth.build();
    }
}
