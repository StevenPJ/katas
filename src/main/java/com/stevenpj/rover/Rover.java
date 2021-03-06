package com.stevenpj.rover;

import lombok.Value;

@Value
class Rover {

    Grid grid;

    public String execute(String commands) {
        for (char command : commands.toCharArray()) {
            if (command == 'R') grid.turnRight();
            if (command == 'L') grid.turnLeft();
            if (command == 'M' && grid.willHitObstacle()) return reportObstaclePosition();
            if (command == 'M') grid.moveForward();
        }
        return grid.getPosition();
    }

    private String reportObstaclePosition() {
        return String.format("O:%s", grid.getPosition());
    }
}
