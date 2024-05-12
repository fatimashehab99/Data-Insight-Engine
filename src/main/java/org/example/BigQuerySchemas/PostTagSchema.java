package org.example.BigQuerySchemas;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;
import org.apache.beam.sdk.transforms.DoFn;
import org.example.Models.PageView;

import java.util.ArrayList;
import java.util.List;

import static org.example.helpers.Constants.*;

public class PostTagSchema {
    public static class PostTags extends DoFn<PageView, TableRow> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            PageView pageView = c.element();

            assert pageView != null;
            for (String tag : pageView.getPostTags()) {
                TableRow row = new TableRow()
                        .set(POST_ID, pageView.getPostId())
                        .set(TAG, tag);

                c.output(row);
            }
        }

        ///to get post tag fields name
        public static TableSchema getPostTagSchema() {
            List<TableFieldSchema> fields = new ArrayList<>();
            fields.add(new TableFieldSchema().setName(POST_ID).setType("STRING"));
            fields.add(new TableFieldSchema().setName(TAG).setType("STRING"));
            return new TableSchema().setFields(fields);
        }
    }
}