package rover

import com.stevenpj.rover.Grid
import spock.lang.Specification
import spock.lang.Unroll

class GridSpec extends Specification {

    def "should have starting position of 0:0:N"() {
        given:
        def grid = new Grid("N")

        expect:
        grid.getPosition() == "0:0:N"
    }

    @Unroll
    def "should rotate right from #initialHeading to #finalHeading"() {
        given: "an initial heading of #heading"
        def grid = new Grid(initialHeading)

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
}
