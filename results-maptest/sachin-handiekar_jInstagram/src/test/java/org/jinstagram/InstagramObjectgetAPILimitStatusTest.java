package org.jinstagram;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;
import org.jinstagram.http.APILimitUtils;
public class InstagramObjectgetAPILimitStatusTest {
    private InstagramObject testObject;
    private Map<String, String> headers;

    @Before
    public void setUp() {
        headers = new HashMap();
        testObject = new InstagramObject() {
            @Override
            public int getAPILimitStatus() {
                return APILimitUtils.getAPILimitStatus(headers);
            }
        };
    }

    @After
    public void tearDown() {
        testObject = null;
        headers = null;
    }
}
