package com.naman.transforms;

import com.naman.constants.Header;
import org.apache.beam.sdk.transforms.DoFn;

public class FilterHeaderFn extends DoFn<String, String> {

    @ProcessElement
    public void processElement(ProcessContext context) {
        var row = context.element();

        assert row != null;
        if (!(row.isBlank() || row.isEmpty()) &&
                !row.equalsIgnoreCase(Header.getHeader())) {
            System.out.println(row);
            context.output(row);
        }
    }

}
