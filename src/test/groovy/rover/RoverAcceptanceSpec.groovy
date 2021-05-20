package rover

import com.stevenpj.rover.Coordinate
import com.stevenpj.rover.Grid
import com.stevenpj.rover.Heading
import com.stevenpj.rover.Rover
import spock.lang.Specification

class RoverAcceptanceSpec extends Specification {

    Grid AT_ORIGIN_FACING_NORTH = new Grid(Coordinate.atOrigin(), new Heading.North())
    Rover rover = new Rover(AT_ORIGIN_FACING_NORTH)

    def "should move rover on a grid"() {
        expect:
        rover.execute("RMMLM") == "2:1:N"
    }

    def "should wrap rover back to start of grid"() {
        expect:
        rover.execute("MMMMMMMMMM") == "0:0:N"
    }
}
