package rover

import com.stevenpj.rover.Grid
import com.stevenpj.rover.Rover
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
    }

    def "should move forward on the grid"() {
        when:
        rover.execute("MMM")

        then:
        3 * grid.moveForward()
    }
}
