/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example;

import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.example.BigQuerySchemas.PageViewSchema;
import org.example.BigQuerySchemas.PostTagSchema;
import org.example.DataTransformation.PageViewsTransformation;
import org.example.Models.Options;
import org.example.Models.PageView;

import static org.example.BigQuerySchemas.PageViewSchema.PageViewsSchema.getPageViewSchema;
import static org.example.BigQuerySchemas.PostTagSchema.PostTags.getPostTagsSchema;

/**
 * A starter example for writing Beam programs.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 * --project=<YOUR_PROJECT_ID>
 * --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 * --runner=DataflowRunner
 */
public class StarterPipeline {
    private static final String PROJECT_ID = "marine-fusion-414420";
    private static final String DATASET_ID = "data_insight_engine";
    private static final String PageViewTable = "page_views";
    private static final String PostTags = "post_tags";


    public static void main(String[] args) {
        // pipeline options
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation()
                .as(Options.class);
        // Set Dataflow specific options
        options.setJobName("my-dataflow-job");
        options.setTempLocation("gs://my-data99/data_insight_engine_pipeline_template");
        options.setProject(PROJECT_ID);
        options.setRegion("us-central1");//set region
        options.setRunner(DataflowRunner.class);


        //creating pipeline
        Pipeline p = Pipeline.create(options);

        //reading from json
        PCollection<FileIO.ReadableFile> jsonLines = p.apply("Read JSON", FileIO.match().filepattern(options.getInputFile()))
                .apply("Read Matches", FileIO.readMatches());

        //transforming json data to page view objects
        PCollection<PageView> pageViews = jsonLines.apply("Read JSON Lines", ParDo.of(new PageViewsTransformation()));

        //Writing to Page Views Big query
        pageViews.apply("Write To Page Views Big Query", ParDo.of(new PageViewSchema.PageViewsSchema()))
                .apply(BigQueryIO.writeTableRows()
                        .to(String.format("%s:%s.%s", PROJECT_ID, DATASET_ID, PageViewTable))
                        .withSchema(getPageViewSchema())
                        .withCustomGcsTempLocation(ValueProvider.StaticValueProvider.of("gs://my-data99/tmp/"))
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED)
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND));

        //Writing to Post Tags Big query
        pageViews.apply("Write To Post Tags Big Query", ParDo.of(new PostTagSchema.PostTags()))
                .apply(BigQueryIO.writeTableRows()
                        .to(String.format("%s:%s.%s", PROJECT_ID, DATASET_ID, PostTags))
                        .withSchema(getPostTagsSchema())
                        .withCustomGcsTempLocation(ValueProvider.StaticValueProvider.of("gs://my-data99/tmp/"))
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED)
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND));


        p.run().waitUntilFinish();
    }
}



