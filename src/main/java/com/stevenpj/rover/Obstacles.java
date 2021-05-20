package com.stevenpj.rover;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Obstacles {

    private final Coordinate coordinate;

    boolean hasObstacleAt(Coordinate coordinate) {
        return coordinate.equals(this.coordinate);
    }

    String decoratePosition(String position) {
        return position;
    }

    boolean haveBeenHit() {
        return false;
    }

}
