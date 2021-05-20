package com.stevenpj.rover

import spock.lang.Specification

class ObstaclesSpec extends Specification {

    def "should return true if the coordinate is an obstacle"() {
        given:
        def coordinate = new Coordinate(2, 5)
        def obstacles = new Obstacles(coordinate)

        expect:
        obstacles.hasObstacleAt(coordinate)
    }

}
