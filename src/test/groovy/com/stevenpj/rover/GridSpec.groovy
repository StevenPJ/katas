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

    def "should return true when an obstacle will be hit"() {
        given: "a grid with an obstacle at the next coordinate"
        def startingCoordinate = Coordinate.atOrigin()
        def nextCoordinate = NORTH.getNext(startingCoordinate)
        def obstacles = Mock(Obstacles) {
            hasObstacleAt(nextCoordinate) >> true
        }
        def grid = new Grid(startingCoordinate, NORTH, obstacles)

        expect: "it will hit an obstacle"
        grid.willHitObstacle()
    }

    static final Heading NORTH = new Heading.North()
    static final Heading EAST = new Heading.East()
    static final Heading SOUTH = new Heading.South()
    static final Heading WEST = new Heading.West()
}
