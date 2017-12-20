package com.angel.web.service.maze.explorer.validator;

public class ContainsAllElementsValidator implements MazeValidator {
    @Override
    public boolean isValid(char[][] maze) {

        int walls = 0;
        int starts = 0;
        int exits = 0;
        int spaces = 0;
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                char element = maze[i][j];
                if (element == 'X') walls++;
                if (element == ' ') spaces++;
                if (element == 'S') starts++;
                if (element == 'F') exits++;
            }
        }
        return !(walls == 0 || starts == 0 || exits == 0 || spaces == 0);
    }
}
