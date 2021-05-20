package com.stevenpj.rover;

import lombok.Getter;

@Getter
class Grid {

    private Coordinate coordinate;
    private Heading heading;
    private final Obstacles obstacles;

    public Grid(Coordinate coordinate, Heading heading, Obstacles obstacle) {
        this.coordinate = coordinate;
        this.heading = heading;
        this.obstacles = obstacle;
    }

    public Grid(Coordinate coordinate, Heading heading) {
        this.coordinate = coordinate;
        this.heading = heading;
        this.obstacles = new Obstacles();
    }

    public String getPosition() {
        String position = String.format("%d:%d:%s", coordinate.getX(), coordinate.getY(), heading.toChar());
        return obstacles.decoratePosition(position);
    }

    public void turnRight() {
        if (obstacles.haveBeenHit()) return;
        heading = heading.toTheRight();
    }

    public void turnLeft() {
        if (obstacles.haveBeenHit()) return;
        heading = heading.toTheLeft();
    }

    public void moveForward() {
        Coordinate nextCoordinate = heading.getNext(coordinate);
        if (obstacles.hasObstacleAt(nextCoordinate)) {
            return;
        };
        coordinate = nextCoordinate;
    }
}

