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
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.example.BigQuerySchemas.PageViewSchema;
import org.example.BigQuerySchemas.PostTagSchema;
import org.example.DataTransformation.PageViewsTransformation;
import org.example.Models.Options;
import org.example.Models.PageView;

import static org.example.BigQuerySchemas.PageViewSchema.PageViewsSchema.getPageViewSchema;
import static org.example.BigQuerySchemas.PostTagSchema.PostTags.getPostTagSchema;
import static org.example.helpers.Constants.*;

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

    public static void main(String[] args) {


        // pipeline options
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation()
                .as(Options.class);

        // Set Dataflow specific options
        options.setJobName("data-insight-engine-job"); //job name
        options.setTempLocation(PATH + "/tmp");//cloud storage temp file location
        options.setProject(PROJECT_ID);// GCP project id
        options.setRegion("us-central1");//set region
        options.setStagingLocation(PATH + "/staging");
        options.setTemplateLocation(PATH + "/template");
        options.setMaxNumWorkers(10);//max workers
        options.setRunner(DataflowRunner.class);//set data flow as runner

        //creating pipeline
        Pipeline p = Pipeline.create(options);

        //reading data from json
        PCollection<FileIO.ReadableFile> jsonLines = p.apply("ReadJSONFile", FileIO.match().filepattern(options.getInputFile()))
                .apply("ParseJSON", FileIO.readMatches());

        //transforming json data to page view objects
        PCollection<PageView> pageViews = jsonLines.apply("TransformData", ParDo.of(new PageViewsTransformation()));

        //parallel branching
        //Writing to Page Views Big query table
        pageViews.apply("ConvertToPageViewsBQ", ParDo.of(new PageViewSchema.PageViewsSchema()))
                .apply("WriteToPageViewsBQ", BigQueryIO.writeTableRows()
                        .to(String.format("%s:%s.%s", PROJECT_ID, DATASET_ID, PAGEVIEWS_TABLE))
                        .withSchema(getPageViewSchema())
                        .withCustomGcsTempLocation(ValueProvider.StaticValueProvider.of("gs://my-data99/tmpBQ/"))
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED)
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND));


        //Writing to Post Tags Big query table
        pageViews.apply("ConvertToPageTagsBQ", ParDo.of(new PostTagSchema.PostTags()))
                .apply("WriteToPageViewsBQ", BigQueryIO.writeTableRows()
                        .to(String.format("%s:%s.%s", PROJECT_ID, DATASET_ID, POST_TAGS_TABLE))
                        .withSchema(getPostTagSchema())
                        .withCustomGcsTempLocation(ValueProvider.StaticValueProvider.of("gs://my-data99/tmpBQ/"))
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED)
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND));

        p.run();
    }
}



