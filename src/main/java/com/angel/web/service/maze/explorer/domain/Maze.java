package com.angel.web.service.maze.explorer.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.angel.web.service.maze.explorer.domain.MazeElement.getMazeElementByInternalRepresentation;

/**
 * Mazes within this service are always rectangular (square included), have one
 * entry and one exit only.
 */
public class Maze {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final char[][] maze;
    private final Point startPosition;
    private final Point size;

    public Maze(char[][] maze) {
        this.maze = maze;
        startPosition = searchStartPosition(maze);
        size = calculateSize(maze);
    }

    public MazeElement getMazeElementAt(final int x, final int y) throws MazeOutOfBoundsException {
        try {
            char element = maze[x][y];
            MazeElement mazeElement = getMazeElementByInternalRepresentation(element);
            return mazeElement;
        }
        catch (IndexOutOfBoundsException e)
        {
            String errorMessage = "Out of bounds coordinate requested: (" + x + ", " + y + ")";
            logger.error(errorMessage);
            throw new MazeOutOfBoundsException(errorMessage);
        }
    }

    private Point searchStartPosition(char[][] maze) {
        Point foundStartPosition = null;
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    foundStartPosition = new Point(i,j);
                }
            }
        }
        return foundStartPosition;
    }

    private Point calculateSize(char[][] maze) {
        int x = maze.length;
        int y = maze[0].length;
        Point size = new Point(x,y);
        return size;
    }
    public Point getStartPosition() {
        return startPosition;
    }

    public Point getSize() {
        return size;
    }

    public char[][] getMaze() {
        return maze;
    }

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
