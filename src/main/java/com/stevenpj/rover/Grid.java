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
        this.obstacles = Obstacles.empty();
    }

    public String getPosition() {
        return String.format("%d:%d:%s", coordinate.getX(), coordinate.getY(), heading.toChar());
    }

    public void turnRight() {
        heading = heading.toTheRight();
    }

    public void turnLeft() {
        heading = heading.toTheLeft();
    }

    public void moveForward() {
        coordinate = heading.getNext(coordinate);
    }

    public boolean willHitObstacle() {
        return obstacles.hasObstacleAt(heading.getNext(coordinate));
    }

    public String reportObstacle() {
        return obstacles.decoratePosition(this.getPosition());
    }
}

