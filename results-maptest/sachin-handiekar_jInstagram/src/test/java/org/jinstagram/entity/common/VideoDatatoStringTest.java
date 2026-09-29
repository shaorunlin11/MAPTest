package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class VideoDatatoStringTest {

    @Test
    public void testToStringWithValidValues() {
        VideoData videoData = new VideoData();
        videoData.setUrl("http://example.com/video.mp4");
        videoData.setWidth(1280);
        videoData.setHeight(720);

        String result = videoData.toString();
        assertEquals("VideoData [videoWidth=1280, videoHeight=720, videoUrl=http://example.com/video.mp4]", result);
    }

    @Test
    public void testToStringWithNullUrl() {
        VideoData videoData = new VideoData();
        videoData.setUrl(null);
        videoData.setWidth(640);
        videoData.setHeight(480);

        String result = videoData.toString();
        assertEquals("VideoData [videoWidth=640, videoHeight=480, videoUrl=null]", result);
    }
}
