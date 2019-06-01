package com.cxa.test.service;

import com.cxa.test.exception.GridPathCalculatorInvalidArgumentException;
import com.cxa.test.model.Graph;
import com.cxa.test.model.Neighbour;
import com.cxa.test.model.Vertex;
import com.cxa.test.utils.DepthFirstSearchContext;
import com.cxa.test.utils.Direction;
import com.cxa.test.utils.GridPathCalculatorContext;
import com.cxa.test.utils.ValidNeighbourContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Optional.ofNullable;

@Service
public class GridPathCalculatorService {

    public static final String INVALID_GRID_SIZE  = "Grid Size should be between 2 and 8";
    public static final String INVALID_NO_OF_BLOCKED_CELLS = "Number of blocked cells should be between 1 and twice " +
            "the grid size";
    public static final String INVALID_BLOCKED_CELLS = "Blocked cells do not match with the number of blocked cells " +
            "specified or user specified a blocked cell as the first or last cell of the grid";

    public Set<Integer> blockedCells;

    public String calculateGridPath(final GridPathCalculatorContext context) {
        validateInputs(context);
        int size = Integer.parseInt(context.getSize());
        Graph graph = constructGraph(context);
        List<Vertex> vertices = graph.getVertices();
        boolean[] visited = new boolean[(size * size) + 1];
        Deque path = depthFirstSearch(DepthFirstSearchContext.newBuilder()
                .setCell(1)
                .setVertices(vertices)
                .setVisited(visited)
                .setPath(new LinkedList<>())
                .build());
        StringBuilder pathTraced = new StringBuilder();
        if (!pathReached(visited, path)) {
            pathTraced.append("No Path Available");
        } else {
            path.descendingIterator().forEachRemaining(pathTraced::append);
        }
        System.out.println(pathTraced);
        return pathTraced.toString();
    }

    private Deque<String> depthFirstSearch(final DepthFirstSearchContext context) {
        int cell = context.getCell();
        boolean[] visited = context.getVisited();
        Deque<String> path = context.getPath();
        List<Vertex> vertices = context.getVertices();
        visited[cell] = true;
        path.push(cell + "");
        if (cell == visited.length - 1) {
            return path;
        }
        path.push(" --> ");
        for (Neighbour neighbour = vertices.stream().filter(v -> v.getCellNum() == cell).findAny().orElse(Vertex
                .newBuilder()
                .build())
                .getAdjacencyList(); neighbour != null; neighbour = neighbour
                .getNext()) {
            int cellNum = neighbour.getCell();
            if (!visited[cellNum]) {
                depthFirstSearch(DepthFirstSearchContext.newBuilder(context)
                        .setCell(cellNum)
                        .build());
                if (pathReached(visited, path)) {
                    break;
                }
            }
        }
        if (StringUtils.equalsIgnoreCase(path.peek(), " --> ")) {
            path.pop();
            path.pop();
        }
        return path;
    }

    private boolean pathReached(boolean[] visited, Deque<String> path) {
        return StringUtils.equalsIgnoreCase(path.peek(), ((visited.length - 1) + ""));
    }

    private Graph constructGraph(final GridPathCalculatorContext contex) {
        List<Vertex> vertices = new ArrayList<>();
        int size = Integer.parseInt(contex.getSize());
        IntStream.range(1, (size * size) + 1)
                .filter(i -> !blockedCells.contains(i))
                .forEach(i -> vertices.add(Vertex.newBuilder()
                        .setCellNum(i)
                        .build()));
        return Graph.newBuilder()
                .setVertices(constructAdjacencyList(vertices, size))
                .build();
    }

    private List<Vertex> constructAdjacencyList(final List<Vertex> vertices, final int size) {
        return vertices.stream().map(v -> {
            Vertex vertex = null;
            int cell = v.getCellNum();
            ValidNeighbourContext context = ValidNeighbourContext.newBuilder()
                    .setDirection(Direction.UP)
                    .setCell(cell - size)
                    .setVertex(cell)
                    .setSize(size)
                    .build();
            if (validNeighbour(context)) {
                vertex = constructNewVertex(v, constructNeighbour(cell - size, v.getAdjacencyList()));
            }
            vertex = ofNullable(vertex).orElse(v);
            if (validNeighbour(ValidNeighbourContext.newBuilder(context)
                    .setCell(cell - size + 1)
                    .setDirection(Direction.DRU)
                    .build())) {
                vertex = constructNewVertex(vertex, constructNeighbour(cell - size + 1, vertex.getAdjacencyList()));
            }
            if (validNeighbour(ValidNeighbourContext.newBuilder(context)
                    .setCell(cell + 1)
                    .setDirection(Direction.RIGHT)
                    .build())) {
                vertex = constructNewVertex(vertex, constructNeighbour(cell + 1, vertex.getAdjacencyList()));
            }
            if (validNeighbour(ValidNeighbourContext.newBuilder(context)
                    .setCell(cell + size + 1)
                    .setDirection(Direction.DRL)
                    .build())) {
                vertex = constructNewVertex(vertex, constructNeighbour(cell + size + 1, vertex.getAdjacencyList()));
            }
            if (validNeighbour(ValidNeighbourContext.newBuilder(context)
                    .setCell(cell + size)
                    .setDirection(Direction.DOWN)
                    .build())) {
                vertex = constructNewVertex(vertex, constructNeighbour(cell + size, vertex.getAdjacencyList()));
            }
            if (validNeighbour(ValidNeighbourContext.newBuilder(context)
                    .setCell(cell + size - 1)
                    .setDirection(Direction.DLD)
                    .build())) {
                vertex = constructNewVertex(vertex, constructNeighbour(cell + size - 1, vertex.getAdjacencyList()));
            }
            if (validNeighbour(ValidNeighbourContext.newBuilder(context)
                    .setCell(cell - 1)
                    .setDirection(Direction.LEFT)
                    .build())) {
                vertex = constructNewVertex(vertex, constructNeighbour(cell - 1, vertex.getAdjacencyList()));
            }
            if (validNeighbour(ValidNeighbourContext.newBuilder(context)
                    .setCell(cell - size - 1)
                    .setDirection(Direction.DLU)
                    .build())) {
                vertex = constructNewVertex(vertex, constructNeighbour(cell - size - 1, vertex.getAdjacencyList()));
            }
            return vertex;
        }).collect(Collectors.toList());
    }

    private boolean validNeighbour(final ValidNeighbourContext context) {
        boolean isValidNeighbour = true;
        int cell = context.getCell();
        int size = context.getSize();
        if (cell < 1 || cell > (size * size) || blockedCells.contains(cell)) {
            isValidNeighbour = false;
        }
        if (isValidNeighbour) {
            switch(context.getDirection()) {
                case LEFT:
                case DLD:
                case DLU:
                    if (cell % size == 0) {
                        isValidNeighbour = false;
                    }
                    break;
                case RIGHT:
                case DRL:
                case DRU:
                    if (cell % size == 1) {
                        isValidNeighbour = false;
                    }
                    break;
                default:
                    break;
            }
        }
        return isValidNeighbour;
    }

    private Vertex constructNewVertex(final Vertex vertex, final Neighbour adjacencyList) {
        return Vertex.newBuilder(vertex)
                .setAdjacencyList(adjacencyList)
                .build();
    }

    private Neighbour constructNeighbour(final int cell, final Neighbour next) {
        return Neighbour.newBuilder()
                .setCell(cell)
                .setNext(next)
                .build();
    }

    private void validateInputs(GridPathCalculatorContext context) {
        int size = ofNullable(context.getSize()).map(i -> {
            int result;
            try {
                result = Integer.parseInt(i.trim());
            } catch (NumberFormatException ex) {
                result = 0;
            }
            return result;
        }).orElse(0);
        if (size < 2 || size > 8) {
            throw new GridPathCalculatorInvalidArgumentException(INVALID_GRID_SIZE);
        }

        int numberOfBlockedCells = ofNullable(context.getNumberOfBlockedCells()).map(i -> {
            int result;
            try {
                result = Integer.parseInt(i.trim());
            } catch (NumberFormatException ex) {
                result = 0;
            }
            return result;
        }).orElse(0);
        if (numberOfBlockedCells < 1 || numberOfBlockedCells >  2 * size) {
            throw new GridPathCalculatorInvalidArgumentException(INVALID_NO_OF_BLOCKED_CELLS);
        }

        List<Integer> blockedCellList = ofNullable(context.getBlockedCellList())
                .map(i -> Arrays.asList(i.trim().split(",")))
                .orElse(Collections.emptyList())
                .stream()
                .map(i -> {
                    int result;
                    try {
                        result = Integer.parseInt(i.trim());
                    } catch (NumberFormatException ex) {
                        result = 0;
                    }
                    return result;
                }).collect(Collectors.toList());
        if (blockedCellList.size() != numberOfBlockedCells) {
            throw new GridPathCalculatorInvalidArgumentException(INVALID_BLOCKED_CELLS);
        }
        if (blockedCellList.size() != blockedCellList.stream().distinct().count()) {
            throw new GridPathCalculatorInvalidArgumentException(INVALID_BLOCKED_CELLS);
        }
        if (blockedCellList.stream().anyMatch(i -> i <= 1 || i >= size * size)) {
            throw new GridPathCalculatorInvalidArgumentException(INVALID_BLOCKED_CELLS);
        }

        blockedCells = new HashSet<>(blockedCellList);
    }
}
