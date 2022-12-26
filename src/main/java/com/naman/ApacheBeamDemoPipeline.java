package com.naman;


import com.naman.options.Options;
import com.naman.transforms.CalculateResultFn;
import com.naman.transforms.FilterHeaderFn;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class ApacheBeamDemoPipeline {
    public static void main(String[] args) {
        var options = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(Options.class);

        run(options);
    }

    static void run(final Options options) {

        // Initialing pipeline
        Pipeline pipeline = Pipeline.create(options);

        PCollection<String> calculatedResult = pipeline.apply("ReadingInputFile",
                        TextIO.read().from(options.getInputFile()))
                .apply("FilteringHeader", ParDo.of(new FilterHeaderFn()))
                .apply("CalculateResult", ParDo.of(new CalculateResultFn()));

        // writing result in a csv file
        calculatedResult.apply("WritingOutputFile", TextIO.write().to(options.getOutput()
                        .concat("result"))
                .withHeader("ID,NAME,AVG")
                .withSuffix(".csv")
                .withNumShards(1)
                .withoutSharding()
                .withOutputFilenames()
                .skipIfEmpty());

        var result = pipeline.run();

        try {
            result.getState();
            result.waitUntilFinish();
        } catch (UnsupportedOperationException e) {
            // do nothing apache beam bug.
        } catch (Exception e) {
            System.out.println("Pipeline Failed");
        }
    }
}
