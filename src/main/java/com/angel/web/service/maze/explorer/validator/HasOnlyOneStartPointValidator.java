package com.angel.web.service.maze.explorer.validator;

public class HasOnlyOneStartPointValidator implements MazeValidator {
    @Override
    public boolean isValid(char[][] maze) {
        int numberOfStarts = 0;
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'F') {
                    if (numberOfStarts > 0) {
                        return false;
                    }
                    numberOfStarts++;
                }
            }
        }
        return numberOfStarts == 1;
    }
}
