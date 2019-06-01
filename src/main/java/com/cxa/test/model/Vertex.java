package com.cxa.test.model;

import static java.util.Optional.ofNullable;

public class Vertex {
    private Integer cellNum;
    private Neighbour adjacencyList;

    public Vertex(Integer cellNum, Neighbour adjacencyList) {
        this.cellNum = cellNum;
        this.adjacencyList = adjacencyList;
    }

    public Integer getCellNum() {
        return cellNum;
    }

    public Neighbour getAdjacencyList() {
        return adjacencyList;
    }

    public static class VertexBuilder {
        private Integer cellNum;
        private Neighbour adjacencyList;

        public VertexBuilder() {
        }

        public VertexBuilder(final Vertex vertex) {
            ofNullable(vertex.getCellNum()).ifPresent(this::setCellNum);
            ofNullable(vertex.getAdjacencyList()).ifPresent(this::setAdjacencyList);
        }

        public VertexBuilder setCellNum(Integer cellNum) {
            this.cellNum = cellNum;
            return this;
        }

        public VertexBuilder setAdjacencyList(Neighbour adjacencyList) {
            this.adjacencyList = adjacencyList;
            return this;
        }

        public Vertex build() {
            return new Vertex(cellNum, adjacencyList);
        }
    }

    public static VertexBuilder newBuilder() {
        return new VertexBuilder();
    }

    public static VertexBuilder newBuilder(final Vertex vertex) {
        return new VertexBuilder(vertex);
    }
}
