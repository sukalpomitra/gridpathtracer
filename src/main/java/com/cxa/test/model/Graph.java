package com.cxa.test.model;

import java.util.List;

public class Graph {
    private List<Vertex> vertices;

    public Graph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public static class GraphBuilder {
        private List<Vertex> vertices;

        public GraphBuilder setVertices(List<Vertex> vertices) {
            this.vertices = vertices;
            return this;
        }

        public Graph build() {
            return new Graph(vertices);
        }
    }

    public static GraphBuilder newBuilder() {
        return new GraphBuilder();
    }
}
