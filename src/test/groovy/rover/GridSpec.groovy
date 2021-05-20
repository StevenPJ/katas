package rover

import com.stevenpj.rover.Grid
import spock.lang.Specification
import spock.lang.Unroll

class GridSpec extends Specification {

    def "should have starting position of 0:0:N"() {
        given:
        def grid = new Grid(0,0  ,"N")

        expect:
        grid.getPosition() == "0:0:N"
    }

    @Unroll
    def "should rotate right from #initialHeading to #finalHeading"() {
        given: "an initial heading of #heading"
        def grid = new Grid(0, 0, initialHeading)

        when: "the rover turns right"
        grid.turnRight()

        then: "the rover should be heading #finalHeading"
        grid.getHeading() == finalHeading

        where:
        initialHeading | finalHeading
        "N"            | "E"
        "E"            | "S"
        "S"            | "W"
        "W"            | "N"
    }

    @Unroll
    def "should rotate left from #initialHeading to #finalHeading"() {
        given: "an initial heading of #heading"
        def grid = new Grid(0, 0, initialHeading)

        when: "the rover turns left"
        grid.turnLeft()

        then: "the rover should be heading #finalHeading"
        grid.getHeading() == finalHeading

        where:
        initialHeading | finalHeading
        "N"            | "W"
        "W"            | "S"
        "S"            | "E"
        "E"            | "N"
    }

    @Unroll
    def "should move to #finalPosition when starting facing #initialHeading"() {
        given: "an initial heading of #heading at 0,0"
        def grid = new Grid(0, 0, initialHeading)

        when: "the rover turns right"
        grid.moveForward()

        then: "the rover should be in #finalPosition"
        grid.getPosition() == finalPosition

        where:
        initialHeading | finalPosition
        "N"            | "0:1:N"
        "W"            | "-1:0:W"
        "S"            | "0:-1:S"
        "E"            | "1:0:E"
    }
}
