package com.stevenpj.rover

import spock.lang.Specification

class ObstaclesSpec extends Specification {

    def "should return false if the coordinate is not an obstacle"() {
        given: "one obstacle at 2,5"
        def obstacles = new Obstacles([new Coordinate(2, 5)])

        expect: "no obstacle at 2, 3"
        !obstacles.hasObstacleAt(new Coordinate(2, 3))
    }

    def "should return true if the coordinate is an obstacle"() {
        given: "two obstacles at 2,5 amd 2,3"
        def coordinate = new Coordinate(2, 5)
        def anotherCoordinate = new Coordinate(2, 3)
        def obstacles = new Obstacles([coordinate, anotherCoordinate])

        expect: "obstacle at 2,3"
        obstacles.hasObstacleAt(new Coordinate(2, 3))
    }

}
