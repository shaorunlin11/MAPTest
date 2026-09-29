package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.comments.CommentData;

public class MediaCommentsFeedtoStringTest {

    @Test
    public void testToString() {
        MediaCommentsFeed feed = new MediaCommentsFeed();

        List<CommentData> commentDataList = new ArrayList<CommentData>();
        Meta meta = new Meta();

        feed.setCommentDataList(commentDataList);
        feed.setMeta(meta);

        String result = feed.toString();

        Assert.assertTrue(result.contains("MediaCommentsFeed [commentDataList="));
        Assert.assertTrue(result.contains(", meta="));
    }
}
