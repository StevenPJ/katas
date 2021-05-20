package com.stevenpj.rover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Grid {

    private Coordinate coordinate;
    private Heading heading;

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

}
