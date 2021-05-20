package com.stevenpj.rover


import spock.lang.Specification
import spock.lang.Unroll

class GridSpec extends Specification {

    def "should have starting position of 0:0:N"() {
        given:
        def grid = new Grid(Coordinate.atOrigin(), NORTH)

        expect:
        grid.getPosition() == "0:0:N"
    }

    @Unroll
    def "should rotate right from #initialHeading.toChar() to #finalHeading.toChar()"() {
        given: "an initial heading of #heading"
        def grid = new Grid(Coordinate.atOrigin(), initialHeading)

        when: "the rover turns right"
        grid.turnRight()

        then: "the rover should be heading #finalHeadingtoChar()"
        grid.getHeading() == finalHeading

        where:
        initialHeading | finalHeading
        NORTH          | EAST
        EAST           | SOUTH
        SOUTH          | WEST
        WEST           | NORTH
    }

    @Unroll
    def "should rotate left from #initialHeadingtoChar() to #finalHeadingtoChar()"() {
        given: "an initial heading of #heading"
        def grid = new Grid(Coordinate.atOrigin(), initialHeading)

        when: "the rover turns left"
        grid.turnLeft()

        then: "the rover should be heading #finalHeadingtoChar()"
        grid.getHeading() == finalHeading

        where:
        initialHeading | finalHeading
        NORTH          | WEST
        WEST           | SOUTH
        SOUTH          | EAST
        EAST           | NORTH
    }

    @Unroll
    def "should move to #finalPosition when starting facing #initialHeading.toChar()"() {
        given: "an initial heading of #headingtoChar() at 0,0"
        def grid = new Grid(Coordinate.atOrigin(), initialHeading)

        when: "the rover moves forward"
        grid.moveForward()

        then: "the rover should be in #finalPosition"
        grid.getPosition() == finalPosition

        where:
        initialHeading | finalPosition
        NORTH          | "0:1:N"
        WEST           | "9:0:W"
        SOUTH          | "0:9:S"
        EAST           | "1:0:E"
    }

    @Unroll
    def "should wrap back to start of grid"() {
        given: "an initial position of #x:#y:#heading"
        def grid = new Grid(new Coordinate(x, y), heading)

        when: "the rover moves forward"
        grid.moveForward()

        then: "the rover should be in #wrappedPosition"
        grid.getPosition() == wrappedPosition

        where:
        x | y | heading | wrappedPosition
        0 | 9 | NORTH   | "0:0:N"
        9 | 0 | EAST    | "0:0:E"
        0 | 0 | SOUTH   | "0:9:S"
        0 | 0 | WEST    | "9:0:W"
    }

    def "should stop at next obstacle"() {
        given: "a grid with obstacle at next coordinate"
        def obstacles = Mock(Obstacles) {
            hasObstacleAt(NORTH.getNext(Coordinate.atOrigin())) >> true
            decoratePosition("0:0:N") >> "O:0:0:N"
            haveBeenHit() >> true
        }
        def grid = new Grid(Coordinate.atOrigin(), NORTH, obstacles)

        when: "the rover moves forward"
        grid.moveForward()

        then: "it hits the obstacle and reports its position"
        grid.getPosition() == "O:0:0:N"

        when: "the rover tries to turn"
        grid.turnRight()

        then: "it still reports the position where it hit the obstacle"
        grid.getPosition() == "O:0:0:N"
    }

    static final Heading NORTH = new Heading.North()
    static final Heading EAST = new Heading.East()
    static final Heading SOUTH = new Heading.South()
    static final Heading WEST = new Heading.West()
}
