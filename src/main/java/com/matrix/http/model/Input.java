package com.matrix.http.model;

public class Input {
    private int [][] matrix;
    private int rowLength;
    private int colLength;

    public Input() {
    }

    public Input(int[][] matrix, int rowLength, int colLength) {
        this.matrix = matrix;
        this.rowLength = rowLength;
        this.colLength = colLength;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getRowLength() {
        return rowLength;
    }

    public int getColLength() {
        return colLength;
    }
}
