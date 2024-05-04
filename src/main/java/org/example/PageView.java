package org.example;

import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.coders.SerializableCoder;

import java.io.Serializable;

@DefaultCoder(SerializableCoder.class)
public class PageView implements Serializable {
    private String postId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public PageView(String postId) {
        postId = postId;
    }
}
