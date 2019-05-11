package com.matrix.core;

public class SubMatrix {
    private int width;
    private int length;
    private int x;
    private int y;
    private int maxArea;

    public SubMatrix() {
    }

    public SubMatrix(int width, int length, int x, int y, int maxArea) {
        this.width = width;
        this.length = length;
        this.x = x;
        this.y = y;
        this.maxArea = maxArea;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMaxArea() {
        return maxArea;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s) length: %s, width %s with area: %s", x, y, length, width, maxArea);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubMatrix subMatrix = (SubMatrix) o;

        if (width != subMatrix.width) return false;
        if (length != subMatrix.length) return false;
        if (x != subMatrix.x) return false;
        if (y != subMatrix.y) return false;
        return maxArea == subMatrix.maxArea;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + length;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + maxArea;
        return result;
    }
}
