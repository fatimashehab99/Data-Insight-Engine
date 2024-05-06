package org.example.BigQuerySchemas;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;
import org.apache.beam.sdk.transforms.DoFn;
import org.example.Models.PageView;

import java.util.ArrayList;
import java.util.List;

public class PostTagSchema {
    public static class PostTags extends DoFn<PageView, TableRow> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            PageView pageView = c.element();

            // Assuming PageView is a class containing getters for fields like PostId and PostTags
            assert pageView != null;
            for (String tag : pageView.getPostTags()) {
                TableRow row = new TableRow()
                        .set("PostId", pageView.getPostId())
                        .set("Name", tag);

                c.output(row);
            }
        }

        public static TableSchema getPostTagsSchema() {
            List<TableFieldSchema> fields = new ArrayList<>();
            fields.add(new TableFieldSchema().setName("PostId").setType("STRING"));
            fields.add(new TableFieldSchema().setName("Name").setType("STRING"));
            return new TableSchema().setFields(fields);
        }
    }
}