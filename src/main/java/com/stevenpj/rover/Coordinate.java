package com.stevenpj.rover;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = wrap(x);
        this.y = wrap(y);
    }

    private int wrap(int value) {
        if (value < 0) return 9;
        if (value > 9) return 0;
        return value;
    }

    public static Coordinate atOrigin() {
        return new Coordinate(0, 0);
    }
}
