package com.stevenpj.rover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Grid {

    private int x;
    private int y;
    private String heading;

    public String getPosition() {
        return String.format("%d:%d:%s", x, y, heading);
    }

    public void turnRight() {
        switch (heading) {
            case "N":
                heading = "E";
                break;
            case "E":
                heading = "S";
                break;
            case "S":
                heading = "W";
                break;
            case "W":
                heading = "N";
                break;
        }
    }

    public void turnLeft() {
        switch (heading) {
            case "N":
                heading = "W";
                break;
            case "W":
                heading = "S";
                break;
            case "S":
                heading = "E";
                break;
            case "E":
                heading = "N";
                break;
        }
    }

    public void moveForward() {
        switch (heading) {
            case "N":
                y += 1;
                break;
            case "W":
                x -= 1;
                break;
            case "S":
                y -= 1;
                break;
            case "E":
                x += 1;
                break;
        }
    }
}
