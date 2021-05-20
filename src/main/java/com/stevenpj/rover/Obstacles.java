package com.stevenpj.rover;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Obstacles {

    private final List<Coordinate> coordinates;

    public static Obstacles empty() {
        return new Obstacles(new ArrayList<>());
    }

    boolean hasObstacleAt(Coordinate coordinate) {
        return this.coordinates.contains(coordinate);
    }

    String decoratePosition(String position) {
        return "O:" + position;
    }
}
