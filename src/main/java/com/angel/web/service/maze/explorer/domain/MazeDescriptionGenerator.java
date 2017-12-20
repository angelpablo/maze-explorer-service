package com.angel.web.service.maze.explorer.domain;

import java.util.HashMap;
import java.util.Map;

public final class MazeDescriptionGenerator {
    private static final Map<Integer,MazeDescription> mazeDescriptions = new HashMap<>();

    private MazeDescriptionGenerator() {

    }

    public static MazeDescription getMazeDescription(Maze maze, Integer level) {
        MazeDescription mazeDescription = mazeDescriptions.get(level);
        if (mazeDescription == null) {
            mazeDescription = generateMazeDescription(maze);
            mazeDescriptions.put(level, mazeDescription);
        }
        return mazeDescription;
    }

    private static MazeDescription generateMazeDescription(Maze maze) {
        int numberOfWalls = 0;
        int numberOfSpaces = 0;
        char[][] mazeLayout = maze.getMaze();
        for(int i = 0; i < mazeLayout.length; i++) {
            for(int j = 0; j < mazeLayout[i].length; j++) {
                if (mazeLayout[i][j] == 'X') {
                    numberOfWalls++;
                }
                if (mazeLayout[i][j] == ' ') {
                    numberOfSpaces++;
                }
            }
        }
        MazeDescription localDescription = new MazeDescription(numberOfWalls,numberOfSpaces);
        return localDescription;
    }
}
