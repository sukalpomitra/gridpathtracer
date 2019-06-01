package com.cxa.test.model;

import static java.util.Optional.ofNullable;

public class Neighbour {
    private int cell;
    private Neighbour next;

    private Neighbour(int cell, Neighbour next) {
        this.cell = cell;
        this.next = next;
    }

    public int getCell() {
        return cell;
    }

    public Neighbour getNext() {
        return next;
    }

    public static class NeighbourBuilder {
        private int cell;
        private Neighbour next;

        public NeighbourBuilder() {
        }

        public NeighbourBuilder(final Neighbour neighbour) {
            this.setCell(neighbour.getCell());
            ofNullable(neighbour.getNext()).ifPresent(this::setNext);
        }

        public NeighbourBuilder setCell(int cell) {
            this.cell = cell;
            return this;
        }

        public NeighbourBuilder setNext(Neighbour next) {
            this.next = next;
            return this;
        }

        public Neighbour build() {
            return new Neighbour(cell, next);
        }
    }

    public static NeighbourBuilder newBuilder() {
        return new NeighbourBuilder();
    }

    public static NeighbourBuilder newBuilder(final Neighbour neighbour) {
        return new NeighbourBuilder(neighbour);
    }
}
