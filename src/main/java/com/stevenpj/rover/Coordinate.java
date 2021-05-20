package com.stevenpj.rover;

import lombok.Value;

@Value
public class Coordinate {
    int x;
    int y;

    public static Coordinate atOrigin() {
        return new Coordinate(0, 0);
    }
}
