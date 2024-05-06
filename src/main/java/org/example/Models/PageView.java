package org.example.Models;

import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.coders.SerializableCoder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@DefaultCoder(SerializableCoder.class)
public class PageView implements Serializable {
    private String PostId;
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

    public void setPostId(String postId) {
        PostId = postId;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getPostType() {
        return PostType;
    }

    public void setPostType(String postType) {
        PostType = postType;
    }

    public String getPostImage() {
        return PostImage;
    }

    public void setPostImage(String postImage) {
        PostImage = postImage;
    }

    public String getPostUrl() {
        return PostUrl;
    }

    public void setPostUrl(String postUrl) {
        PostUrl = postUrl;
    }

    public String getPostCategory() {
        return PostCategory;
    }

    public void setPostCategory(String postCategory) {
        PostCategory = postCategory;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPostPublishDate() {
        return PostPublishDate;
    }

    public void setPostPublishDate(String postPublishDate) {
        PostPublishDate = postPublishDate;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public List<String> getPostTags() {
        return PostTags;
    }

    public void setPostTags(List<String> postTags) {
        PostTags = postTags;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

}
