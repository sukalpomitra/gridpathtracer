package com.cxa.test.utils;

import com.cxa.test.model.Vertex;

import java.util.Deque;
import java.util.List;

import static java.util.Optional.ofNullable;

public class DepthFirstSearchContext {
    private List<Vertex> vertices;
    private boolean[] visited;
    private int cell;
    private Deque<String> path;

    public DepthFirstSearchContext(List<Vertex> vertices, boolean[] visited, int cell, Deque<String> path) {
        this.vertices = vertices;
        this.visited = visited;
        this.cell = cell;
        this.path = path;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public boolean[] getVisited() {
        return visited;
    }

    public int getCell() {
        return cell;
    }

    public Deque<String> getPath() {
        return path;
    }

    public static class DepthFirstSearchContextBuilder {
        private List<Vertex> vertices;
        private boolean[] visited;
        private int cell;
        private Deque<String> path;

        public DepthFirstSearchContextBuilder() {
        }

        public DepthFirstSearchContextBuilder(final DepthFirstSearchContext context) {
            ofNullable(context.getPath()).ifPresent(this::setPath);
            ofNullable(context.getVertices()).ifPresent(this::setVertices);
            ofNullable(context.getVisited()).ifPresent(this::setVisited);
            this.setCell(context.getCell());
        }

        public DepthFirstSearchContextBuilder setVertices(List<Vertex> vertices) {
            this.vertices = vertices;
            return this;
        }

        public DepthFirstSearchContextBuilder setVisited(boolean[] visited) {
            this.visited = visited;
            return this;
        }

        public DepthFirstSearchContextBuilder setCell(int cell) {
            this.cell = cell;
            return this;
        }

        public DepthFirstSearchContextBuilder setPath(Deque<String> path) {
            this.path = path;
            return this;
        }

        public DepthFirstSearchContext build() {
            return new DepthFirstSearchContext(vertices, visited, cell, path);
        }
    }

    public static DepthFirstSearchContextBuilder newBuilder() {
        return new DepthFirstSearchContextBuilder();
    }

    public static DepthFirstSearchContextBuilder newBuilder(final DepthFirstSearchContext context) {
        return new DepthFirstSearchContextBuilder(context);
    }
}
