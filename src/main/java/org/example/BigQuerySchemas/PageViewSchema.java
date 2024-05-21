package org.example.BigQuerySchemas;

import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.sdk.transforms.DoFn;
import org.example.Models.PageView;
import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;

import java.util.ArrayList;
import java.util.List;

import static org.example.helpers.Constants.*;

public class PageViewSchema {
    public static class PageViewsSchema extends DoFn<PageView, TableRow> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            PageView pageview = c.element();

            assert pageview != null;
            TableRow row = new TableRow()
                    .set(POST_ID, pageview.getPostId())
                    .set(IP, pageview.getIp())
                    .set(BROWSER, pageview.getBrowser())
                    .set(DEVICE, pageview.getDevice())
                    .set(POST_TYPE, pageview.getPostType())
                    .set(POST_IMAGE, pageview.getPostImage())
                    .set(POST_URL, pageview.getPostUrl())
                    .set(POST_CATEGORY, pageview.getPostCategory())
                    .set(DOMAIN, pageview.getDomain())
                    .set(USER_ID, pageview.getUserId())
                    .set(Post_Publish_Date, pageview.getPostPublishDate())
                    .set(DATE, pageview.getDate())
                    .set(POST_TAG, pageview.getPostTags())
                    .set(POST_AUTHOR, pageview.getPostAuthor())
                    .set(COUNTRY_NAME, pageview.getCountryName())
                    .set(COUNTRY_CODE, pageview.getCountryCode());
            c.output(row);
        }

        ///to get page view fields name
        public static TableSchema getPageViewSchema() {
            List<TableFieldSchema> fields = new ArrayList<>();
            fields.add(new TableFieldSchema().setName(POST_ID).setType("STRING"));
            fields.add(new TableFieldSchema().setName(IP).setType("STRING"));
            fields.add(new TableFieldSchema().setName(BROWSER).setType("STRING"));
            fields.add(new TableFieldSchema().setName(DEVICE).setType("STRING"));
            fields.add(new TableFieldSchema().setName(POST_TYPE).setType("STRING"));
            fields.add(new TableFieldSchema().setName(POST_IMAGE).setType("STRING"));
            fields.add(new TableFieldSchema().setName(POST_URL).setType("STRING"));
            fields.add(new TableFieldSchema().setName(POST_CATEGORY).setType("STRING"));
            fields.add(new TableFieldSchema().setName(DOMAIN).setType("STRING"));
            fields.add(new TableFieldSchema().setName(USER_ID).setType("STRING"));
            fields.add(new TableFieldSchema().setName(Post_Publish_Date).setType("STRING"));
            fields.add(new TableFieldSchema().setName(DATE).setType("STRING"));
            fields.add(new TableFieldSchema().setName(COUNTRY_NAME).setType("STRING"));
            fields.add(new TableFieldSchema().setName(POST_AUTHOR).setType("STRING"));
            fields.add(new TableFieldSchema().setName(POST_TAG).setType("STRING").setMode("REPEATED"));
            fields.add(new TableFieldSchema().setName(COUNTRY_CODE).setType("STRING"));
            return new TableSchema().setFields(fields);
        }
    }
}
