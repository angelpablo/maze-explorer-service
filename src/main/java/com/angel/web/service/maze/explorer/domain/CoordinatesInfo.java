package com.angel.web.service.maze.explorer.domain;

public class CoordinatesInfo {
    private final int x;
    private final int y;
    private final MazeElement mazeElement;

    public CoordinatesInfo(int x, int y, MazeElement mazeElement) {
        this.x = x;
        this.y = y;
        this.mazeElement = mazeElement;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MazeElement getMazeElement() {
        return mazeElement;
    }
}
