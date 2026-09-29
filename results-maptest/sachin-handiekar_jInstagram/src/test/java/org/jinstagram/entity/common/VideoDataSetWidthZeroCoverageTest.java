package org.jinstagram.entity.common;

import org.junit.Test;

public class VideoDataSetWidthZeroCoverageTest {
    @Test
    public void testSetWidthWithNonZeroValue() {
        VideoData videoData = new VideoData();
        videoData.setWidth(1080);
    }
}
