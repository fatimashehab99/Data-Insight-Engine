package org.example.BigQuerySchemas;

import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.sdk.transforms.DoFn;
import org.example.Models.PageView;
import org.joda.time.Instant;
import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;

import java.util.ArrayList;
import java.util.List;

public class PageViewSchema {
    public static class PageViewsSchema extends DoFn<PageView, TableRow> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            PageView pageview = c.element();

            assert pageview != null;
            TableRow row = new TableRow()
                    .set("PostId", pageview.getPostId())
                    .set("Ip", pageview.getIp())
                    .set("Browser", pageview.getBrowser())
                    .set("Device", pageview.getDevice())
                    .set("PostType", pageview.getPostType())
                    .set("PostImage", pageview.getPostImage())
                    .set("PostUrl", pageview.getPostUrl())
                    .set("PostCategory", pageview.getPostCategory())
                    .set("Domain", pageview.getDomain())
                    .set("UserId", pageview.getUserId())
                    .set("PostPublishDate", pageview.getPostPublishDate())
                    .set("Date", pageview.getDate())
                    .set("CountryName", pageview.getCountryName())
                    .set("CountryCode", pageview.getCountryCode());
            c.output(row);
        }

        ///to get page view fields name
        public static TableSchema getPageViewSchema() {
            List<TableFieldSchema> fields = new ArrayList<>();
            fields.add(new TableFieldSchema().setName("PostId").setType("STRING"));
            fields.add(new TableFieldSchema().setName("Ip").setType("STRING"));
            fields.add(new TableFieldSchema().setName("Browser").setType("STRING"));
            fields.add(new TableFieldSchema().setName("Device").setType("STRING"));
            fields.add(new TableFieldSchema().setName("PostType").setType("STRING"));
            fields.add(new TableFieldSchema().setName("PostImage").setType("STRING"));
            fields.add(new TableFieldSchema().setName("PostUrl").setType("STRING"));
            fields.add(new TableFieldSchema().setName("PostCategory").setType("STRING"));
            fields.add(new TableFieldSchema().setName("Domain").setType("STRING"));
            fields.add(new TableFieldSchema().setName("UserId").setType("STRING"));
            fields.add(new TableFieldSchema().setName("PostPublishDate").setType("STRING"));
            fields.add(new TableFieldSchema().setName("Date").setType("STRING"));
            fields.add(new TableFieldSchema().setName("CountryName").setType("STRING"));
            fields.add(new TableFieldSchema().setName("CountryCode").setType("STRING"));
            return new TableSchema().setFields(fields);
        }
    }
}
