package com.angel.web.service.maze.explorer.validator;

public class IsItRectangularMazeValidator implements MazeValidator {
    @Override
    public boolean isValid(char[][] maze) {
        // three columns should be the minimum?
        if (maze == null || maze.length < 3) {
            return false;
        }
        int secondDimension = 0;
        for(int i = 0; i < maze.length; i++) {
            if (maze[i] == null || maze[i].length < 3) { // a 3x3 should be the minimum
                return false;
            }
            if (i == 0) {
                secondDimension = maze[i].length;  // this one will rule
            } else {
                if (secondDimension != maze[i].length) {
                    return false;
                }
            }
        }
        return true;
    }
}
