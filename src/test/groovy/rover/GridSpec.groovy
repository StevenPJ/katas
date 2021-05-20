package rover

import com.stevenpj.rover.Coordinate
import com.stevenpj.rover.Grid
import com.stevenpj.rover.Heading
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
    def "should move to #finalPosition when starting facing #initialHeadingtoChar()"() {
        given: "an initial heading of #headingtoChar() at 0,0"
        def grid = new Grid(Coordinate.atOrigin(), initialHeading)

        when: "the rover turns right"
        grid.moveForward()

        then: "the rover should be in #finalPosition"
        grid.getPosition() == finalPosition

        where:
        initialHeading | finalPosition
        NORTH          | "0:1:N"
        WEST           | "-1:0:W"
        SOUTH          | "0:-1:S"
        EAST           | "1:0:E"
    }

    static final Heading NORTH = new Heading.North()
    static final Heading EAST = new Heading.East()
    static final Heading SOUTH = new Heading.South()
    static final Heading WEST = new Heading.West()
}
