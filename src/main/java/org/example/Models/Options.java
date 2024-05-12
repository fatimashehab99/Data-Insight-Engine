package org.example.Models;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;
import org.apache.beam.sdk.options.ValueProvider;

public interface Options extends DataflowPipelineOptions {
    @Validation.Required
    @Description("Path of the file to read from")
    ValueProvider<String> getInputFile();

    void setInputFile(ValueProvider<String> value);
}
