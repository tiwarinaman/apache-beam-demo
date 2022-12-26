package com.naman.constants;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Header {
    ID(0),
    NAME(1),
    CHEMISTRY(2),
    MATHEMATICS(3),
    PHYSICS(4);

    private final int index;

    Header(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static String getHeader() {
        return Arrays.stream(Header.values())
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }
}
