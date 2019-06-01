package com.cxa.test.utils;

public class GridPathCalculatorContext {
    private String size;
    private String numberOfBlockedCells;
    private String blockedCellList;

    private GridPathCalculatorContext() {
    }

    private GridPathCalculatorContext(String size, String numberOfBlockedCells, String blockedCellList) {
        this.size = size;
        this.numberOfBlockedCells = numberOfBlockedCells;
        this.blockedCellList = blockedCellList;
    }

    public String getSize() {
        return size;
    }

    public String getNumberOfBlockedCells() {
        return numberOfBlockedCells;
    }

    public String getBlockedCellList() {
        return blockedCellList;
    }

    public static class GridPathCalculatorContextBuilder {
        private String size;
        private String numberOfBlockedCells;
        private String blockedCellList;

        public GridPathCalculatorContextBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        public GridPathCalculatorContextBuilder setNumberOfBlockedCells(String numberOfBlockedCells) {
            this.numberOfBlockedCells = numberOfBlockedCells;
            return this;
        }

        public GridPathCalculatorContextBuilder setBlockedCellList(String blockedCellList) {
            this.blockedCellList = blockedCellList;
            return this;
        }

        public GridPathCalculatorContext build() {
            return new GridPathCalculatorContext(size, numberOfBlockedCells, blockedCellList);
        }
    }

    public static GridPathCalculatorContextBuilder newBuilder() {
        return new GridPathCalculatorContextBuilder();
    }
}
