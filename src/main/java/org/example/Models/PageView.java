package org.example.Models;

import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.coders.SerializableCoder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@DefaultCoder(SerializableCoder.class)
public class PageView implements Serializable {
    private String PostId ;
    private String Ip;
    private String Browser;
    private String Device;
    private String PostType;
    private String PostImage;
    private String PostUrl;
    private String PostCategory;

    private String Domain;
    private String UserId;
    private String PostPublishDate;
    private String Date;
    private List<String> PostTags;
    private String CountryName;
    private String CountryCode;

    public PageView(String postId, String ip, String browser, String device, String postType, String postImage, String postUrl, String postCategory, String domain, String userId, String postPublishDate, String date, List<String> postTags, String countryName, String countryCode) {
        PostId = postId;
        Ip = ip;
        Browser = browser;
        Device = device;
        PostType = postType;
        PostImage = postImage;
        PostUrl = postUrl;
        PostCategory = postCategory;
        Domain = domain;
        UserId = userId;
        PostPublishDate = postPublishDate;
        Date = date;
        PostTags = postTags;
        CountryName = countryName;
        CountryCode = countryCode;
    }

    public String getPostId() {
        return PostId;
    }

    public String getIp() {
        return Ip;
    }

    public String getBrowser() {
        return Browser;
    }

    public String getDevice() {
        return Device;
    }

    public String getPostType() {
        return PostType;
    }

    public String getPostImage() {
        return PostImage;
    }

    public String getPostUrl() {
        return PostUrl;
    }

    public String getPostCategory() {
        return PostCategory;
    }

    public String getDomain() {
        return Domain;
    }

    public String getUserId() {
        return UserId;
    }

    public String getPostPublishDate() {
        return PostPublishDate;
    }

    public String getDate() {
        return Date;
    }

    public List<String> getPostTags() {
        return PostTags;
    }

    public String getCountryName() {
        return CountryName;
    }

    public String getCountryCode() {
        return CountryCode;
    }
}
