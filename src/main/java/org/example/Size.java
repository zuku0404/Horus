package org.example;

import java.util.Arrays;
import java.util.Optional;

public enum Size {
    LARGE, MEDIUM, SMALL;

    public static boolean isSizeExist(String s) {
        Optional<Size> first = Arrays.stream(Size.values())
                .filter(size -> size.name().equals(s.toUpperCase()))
                .findFirst();
        return first.isPresent();
    }
}
