package com.naman.options;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface Options extends PipelineOptions {

    @Description("Input file path for pipeline")
    @Default.String("C:\\Users\\naman\\Desktop\\apach-beam-demo-files\\students.csv")
    String getInputFile();

    void setInputFile(String value);

    @Description("Output file path for pipeline")
    @Default.String("C:\\Users\\naman\\Desktop\\apach-beam-demo-files\\")
    String getOutput();

    void setOutput(String value);

}
