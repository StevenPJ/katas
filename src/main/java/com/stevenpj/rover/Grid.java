package com.stevenpj.rover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Grid {

    private String heading;

    public String getPosition() {
        return "0:0:N";
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
        throw new UnsupportedOperationException();
    }
}
