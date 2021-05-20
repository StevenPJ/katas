package rover

import com.stevenpj.rover.Grid
import com.stevenpj.rover.Rover
import spock.lang.Specification

class RoverAcceptanceSpec extends Specification {

    def rover = new Rover(new Grid(0, 0, "N"))

    def "should move rover on a grid"() {
        expect:
        rover.execute("RMMLM") == "2:1:N"
    }
}
