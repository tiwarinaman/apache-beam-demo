package com.naman.transforms;

import org.apache.beam.sdk.transforms.DoFn;

import static com.naman.constants.Header.*;

public class CalculateResultFn extends DoFn<String, String> {

    @ProcessElement
    public void processElement(ProcessContext context) {
        var row = context.element();
        var studentData = row.split(",", -1);

        var total = Integer.parseInt(studentData[CHEMISTRY.getIndex()]) +
                Integer.parseInt(studentData[MATHEMATICS.getIndex()]) +
                Integer.parseInt(studentData[PHYSICS.getIndex()]);

        var avg = total / 3;

        var result = studentData[ID.getIndex()]
                .concat(",")
                .concat(studentData[NAME.getIndex()])
                .concat(",")
                .concat(String.valueOf(avg));

        context.output(result);
    }

}
