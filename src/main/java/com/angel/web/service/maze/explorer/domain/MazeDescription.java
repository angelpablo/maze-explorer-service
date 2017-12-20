package com.angel.web.service.maze.explorer.domain;

import java.util.Objects;

public class MazeDescription {
    private final int numberOfWalls;
    private final int numberOfSpaces;

    public MazeDescription(int numberOfWalls, int numberOfSpaces) {
        this.numberOfWalls = numberOfWalls;
        this.numberOfSpaces = numberOfSpaces;
    }

    public int getNumberOfWalls() {
        return numberOfWalls;
    }

    public int getNumberOfSpaces() {
        return numberOfSpaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MazeDescription)) return false;
        MazeDescription that = (MazeDescription) o;
        return numberOfWalls == that.numberOfWalls &&
                numberOfSpaces == that.numberOfSpaces;
    }

    @Override
    public int hashCode() {

        return Objects.hash(numberOfWalls, numberOfSpaces);
    }
}
