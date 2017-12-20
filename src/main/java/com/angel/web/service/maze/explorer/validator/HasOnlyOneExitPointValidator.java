package com.angel.web.service.maze.explorer.validator;

public class HasOnlyOneExitPointValidator implements MazeValidator {
    @Override
    public boolean isValid(char[][] maze) {
        int numberOfExits = 0;
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'F') {
                    if (numberOfExits > 0) {
                        return false;
                    }
                    numberOfExits++;
                }
            }
        }
        return numberOfExits == 1;
    }
}
