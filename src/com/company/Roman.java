package com.company;

import java.util.Arrays;
import java.util.List;

public enum Roman {
    C(100), XC(90), L(50), XL(40), X(10),
    IX(9), V(5), IV(4), I(1);

    private final int value;

    Roman(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<Roman> getReverseSortedValues() {
        return Arrays.asList(values());
    }
}
