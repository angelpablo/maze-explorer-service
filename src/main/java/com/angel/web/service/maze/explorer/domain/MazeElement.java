package com.angel.web.service.maze.explorer.domain;

public enum MazeElement {
    WALL('X'), SPACE(' '), START('S'), EXIT('F');

    private char internalElementRepresentation;

    MazeElement(char element) {
        internalElementRepresentation = element;
    }

    public static MazeElement getMazeElementByInternalRepresentation(char element) {
        if (element == 'X') {
            return WALL;
        }
        if (element == ' ') {
            return SPACE;
        }
        if (element == 'F') {
            return EXIT;
        }

        // mazes were validated beforehand so there won't be any surprises there.
        return START;
    }
}
