package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeFacilitator;
import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import com.angel.web.service.maze.explorer.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class MovementOptionsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MazeFacilitator mazeFacilitator;

    @RequestMapping(method = RequestMethod.GET, value = "/movement-options")
    public Collection<PlayerDirection> getMovementOptions(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException, GameNotStartedException {
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);

        PlayerPosition lastPosition = mazeSession.getLastPosition();
        if (lastPosition == null) {
            logger.error("Attempt to change looup possible moves on a non-started game: [sessionId=" + sessionId + "]");
            throw new GameNotStartedException("No active game");
        }

        Set<PlayerDirection> options = new HashSet<>();
        Maze maze = mazeFacilitator.getMazeByLevel(mazeSession.getLevel());
        if (isAllowedMovingToPosition(lastPosition.getX(), lastPosition.getY() + 1, maze)) {
            options.add(PlayerDirection.EAST);
        }
        if (isAllowedMovingToPosition(lastPosition.getX(), lastPosition.getY() - 1, maze)) {
            options.add(PlayerDirection.WEST);
        }
        if (isAllowedMovingToPosition(lastPosition.getX() + 1, lastPosition.getY(), maze)) {
            options.add(PlayerDirection.NORTH);
        }
        if (isAllowedMovingToPosition(lastPosition.getX() - 1, lastPosition.getY(), maze)) {
            options.add(PlayerDirection.SOUTH);
        }

        return options;
    }

    private boolean isAllowedMovingToPosition(int x, int y, Maze maze) {
        try {
            MazeElement mazeElementAt = maze.getMazeElementAt(x, y);
            switch (mazeElementAt) {
                case WALL:
                    return false;
                case SPACE:
                    return true;
                case START:
                    return true;
                case EXIT:
                    return true;
            }
        } catch (MazeOutOfBoundsException e) {
            return false;
        }
        return false;
    }
}
