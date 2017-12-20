package com.angel.web.service.maze.explorer;

import com.angel.web.service.maze.explorer.domain.Maze;
import com.angel.web.service.maze.explorer.domain.PlayerPosition;

import java.util.Arrays;

public class MazeSession {
    private final Integer level;
    private final Long sessionId;
    private char[][] playerMoves;
    private PlayerPosition lastPosition;

    public MazeSession(Integer level, Long sessionNumber) {
        this.level = level;
        this.sessionId = sessionNumber;
    }

    public Integer getLevel() {
        return level;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setPlayerMovesSizeBasedOnMazeSize(Maze maze) {
        int x = maze.getMaze().length;
        int y = maze.getMaze()[0].length;
        playerMoves = new char[x][y];
        for (int i = 0; i < playerMoves.length; i++) {
            Arrays.fill(playerMoves[i], ' ');
        }
    }

    public char[][] getPlayerMoves() {
        return playerMoves;
    }

    public void setLastPosition(PlayerPosition lastPosition) {
        this.lastPosition = lastPosition;
        // record also the movement
        playerMoves[lastPosition.getX()][lastPosition.getY()] = 'V'; // Visited  :)
    }

    public PlayerPosition getLastPosition() {
        return lastPosition;
    }
}
