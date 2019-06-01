package com.cxa.test.utils;

import static java.util.Optional.ofNullable;

public class ValidNeighbourContext {
    private Direction direction;
    private int vertex;
    private int cell;
    private int size;

    public ValidNeighbourContext(Direction direction, int cell, int size, int vertex) {
        this.direction = direction;
        this.cell = cell;
        this.size = size;
        this.vertex = vertex;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCell() {
        return cell;
    }

    public int getSize() {
        return size;
    }

    public int getVertex() {
        return vertex;
    }

    public static class ValidNeighbourContextBuilder {
        private Direction direction;
        private int cell;
        private int size;
        private int vertex;

        public ValidNeighbourContextBuilder() {
        }

        public ValidNeighbourContextBuilder(final ValidNeighbourContext context) {
            ofNullable(context.getDirection()).ifPresent(this::setDirection);
            this.setSize(context.getSize());
            this.setCell(context.getCell());
            this.setVertex(context.getVertex());
        }

        public ValidNeighbourContextBuilder setDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public ValidNeighbourContextBuilder setCell(int cell) {
            this.cell = cell;
            return this;
        }

        public ValidNeighbourContextBuilder setSize(int size) {
            this.size = size;
            return this;
        }

        public ValidNeighbourContextBuilder setVertex(int vertex) {
            this.vertex = vertex;
            return this;
        }

        public ValidNeighbourContext build() {
            return new ValidNeighbourContext(direction, cell, size, vertex);
        }
    }

    public static ValidNeighbourContextBuilder newBuilder() {
        return new ValidNeighbourContextBuilder();
    }

    public static ValidNeighbourContextBuilder newBuilder(final ValidNeighbourContext context) {
        return new ValidNeighbourContextBuilder(context);
    }
}
