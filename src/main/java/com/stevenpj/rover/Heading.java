package com.stevenpj.rover;

import lombok.EqualsAndHashCode;

public interface Heading {
    Heading toTheRight();

    Heading toTheLeft();

    char toChar();

    Coordinate getNext(Coordinate coordinate);

    @EqualsAndHashCode
    class North implements Heading {

        @Override
        public Heading toTheRight() {
            return new East();
        }

        @Override
        public Heading toTheLeft() {
            return new West();
        }

        @Override
        public char toChar() {
            return 'N';
        }

        @Override
        public Coordinate getNext(Coordinate coordinate) {
            return new Coordinate(coordinate.getX(), coordinate.getY() + 1);
        }
    }

    @EqualsAndHashCode
    class East implements Heading {

        @Override
        public Heading toTheRight() {
            return new South();
        }

        @Override
        public Heading toTheLeft() {
            return new North();
        }

        @Override
        public char toChar() {
            return 'E';
        }

        @Override
        public Coordinate getNext(Coordinate coordinate) {
            return new Coordinate(coordinate.getX() + 1, coordinate.getY());
        }
    }

    @EqualsAndHashCode
    class South implements Heading {

        @Override
        public Heading toTheRight() {
            return new West();
        }

        @Override
        public Heading toTheLeft() {
            return new East();
        }

        @Override
        public char toChar() {
            return 'S';
        }

        @Override
        public Coordinate getNext(Coordinate coordinate) {
            return new Coordinate(coordinate.getX(), coordinate.getY() - 1);
        }
    }

    @EqualsAndHashCode
    class West implements Heading {

        @Override
        public Heading toTheRight() {
            return new North();
        }

        @Override
        public Heading toTheLeft() {
            return new South();
        }

        @Override
        public char toChar() {
            return 'W';
        }

        @Override
        public Coordinate getNext(Coordinate coordinate) {
            return new Coordinate(coordinate.getX() - 1, coordinate.getY());
        }
    }
}
