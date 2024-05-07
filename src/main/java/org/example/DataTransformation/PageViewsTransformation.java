package org.example.DataTransformation;

import com.google.gson.*;

import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.example.Models.PageView;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.exception.GeoIp2Exception;


public class PageViewsTransformation extends DoFn<FileIO.ReadableFile, PageView> {
    @ProcessElement
    public void processElement(ProcessContext c) {
        FileIO.ReadableFile file = c.element();
        try {
            //reading from json
            assert file != null;
            String json = file.readFullyAsUTF8String();
            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

            //looping over the array of json
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();

                //get data
                String postId = jsonObject.get("PostId").getAsString();
                String ip = jsonObject.get("Ip").getAsString();
                String browser = jsonObject.get("Browser").getAsString();
                String device = jsonObject.get("Device").getAsString();
                String postType = jsonObject.get("PostType").getAsString();
                String postImage = jsonObject.get("PostImage").getAsString();
                String postUrl = jsonObject.get("PostUrl").getAsString();
                String postCategory = jsonObject.get("PostCategory").getAsString();
                String domain = jsonObject.get("Domain").getAsString();
                String userId = jsonObject.get("UserId").getAsString();
                String CountryName = jsonObject.get("CountryName").getAsString();
                String CountryCode = jsonObject.get("CountryCode").getAsString();

                // get and format post publish date and date
                String postPublishDate = extractAndFormatDate(jsonObject, "PostPublishDate");
                String date = extractAndFormatDate(jsonObject, "Date");

                // Get PostTags
                JsonArray postTagsArray = jsonObject.getAsJsonArray("PostTags");



                // Extract postTags into a list
                List<String> postTags = new ArrayList<>();
                for (JsonElement tagElement : postTagsArray) {
                    postTags.add(tagElement.getAsString());
                }
                //create page view object
                PageView pageView = new PageView(postId, ip, browser, device, postType, postImage, postUrl, postCategory, domain,
                        userId, postPublishDate, date, postTags, CountryName, CountryCode);

                c.output(pageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///This function is used to format dates
    public static String extractAndFormatDate(JsonObject jsonObject, String fieldName) {
        String dateString = jsonObject.getAsJsonObject(fieldName).get("$date").getAsString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date date = dateFormat.parse(dateString);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}


