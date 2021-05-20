package com.stevenpj.rover


import spock.lang.Specification

class RoverSpec extends Specification {

    def grid = Mock(Grid)
    def rover = new Rover(grid)

    def "should print the rovers starting position on the grid"() {
        given:
        grid.getPosition() >> "0:0:N"

        expect:
        rover.execute("") == "0:0:N"
    }

    def "should rotate on the grid"() {
        when:
        rover.execute("RRRR")

        then:
        4 * grid.turnRight()

        when:
        rover.execute("LLL")

        then:
        3 * grid.turnLeft()
        1 * grid.getPosition()
    }

    def "should move forward on the grid"() {
        when:
        rover.execute("MMM")

        then:
        3 * grid.moveForward()
        1 * grid.getPosition()
    }

    def "should stop once an obstacle is hit"() {
        when: "the rover hits the obstacle at the end"
        rover.execute("RLMM")

        then:  "the rover turned right"
        1 * grid.turnRight()

        then: "the rover turned left"
        1 * grid.turnLeft()

        then: "the rover moved forwards after checking for obstacles"
        1 * grid.willHitObstacle() >> false
        1 * grid.moveForward()

        then: "the rover reported the obstacle"
        1 * grid.willHitObstacle() >> true
        1 * grid.reportObstacle()

        then: "no more interactions"
        0 * _
    }
}
