package org.jinstagram;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.entity.media.MediaInfoFeed;
import org.jinstagram.model.Methods;
import org.jinstagram.utils.Preconditions;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.jinstagram.http.Verbs;
import org.jinstagram.model.Relationship;
import org.jinstagram.entity.relationships.RelationshipFeed;
public class InstagramBasegetMediaInfoTest {
    private InstagramBase instagramBase;
    private Field configField;
    private Field requestProxyField;

    @Before
    public void setUp() throws Exception {
        // Use a concrete subclass if available, otherwise use a mock
        // Since no concrete subclass is available, we'll use a mock
        instagramBase = new InstagramBase(new InstagramConfig()) {
            // Override the abstract method to make it instantiable
            @Override
            public MediaInfoFeed getMediaInfo(String mediaId) throws InstagramException {
                return null;
            }
        };
        configField = InstagramBase.class.getDeclaredField("config");
        configField.setAccessible(true);
        requestProxyField = InstagramBase.class.getDeclaredField("requestProxy");
        requestProxyField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        instagramBase = null;
        configField = null;
        requestProxyField = null;
    }


    @Test
    public void testGetMediaInfoWithNullMediaId() throws Exception {
        ExpectedException exceptionRule = ExpectedException.none();
        exceptionRule.expect(InstagramException.class);
        exceptionRule.expectMessage("mediaId cannot be null.");
        Method method = InstagramBase.class.getDeclaredMethod("getMediaInfo", String.class);
        method.setAccessible(true);
        method.invoke(instagramBase, (String) null);
    }
}
