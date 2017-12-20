package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeExplorerServiceApplication;
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

@RestController
public class MoveForwardController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MazeFacilitator mazeFacilitator;

    @RequestMapping(method = RequestMethod.PUT, value = "/forward")
    public PlayerPosition moveForward(@RequestParam(value = "sessionId") Long sessionId) {
        if (sessionId == null || sessionId.compareTo(Long.valueOf(0)) == 0) {
            return CotrollerUtils.createNoSessionPlayerPosition();
        }
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);
        PlayerPosition lastPosition = mazeSession.getLastPosition();
        if (lastPosition == null) {
            return CotrollerUtils.createNoGameStartedPlayerPosition();
        }

        Maze maze = mazeFacilitator.getMazeByLevel(mazeSession.getLevel());
        Maze.Point size = maze.getSize();
        int newX = 0;
        int newY = 0;

        PlayerDirection direction = lastPosition.getDirection();
        switch (direction) {
            case NORTH:
                newX = lastPosition.getX() + 1;
                newY = lastPosition.getY();
                break;
            case SOUTH:
                newX = lastPosition.getX() - 1;
                newY = lastPosition.getY();
                break;
            case EAST:
                newX = lastPosition.getX();
                newY = lastPosition.getY() + 1;
                break;
            case WEST:
                newX = lastPosition.getX();
                newY = lastPosition.getY() - 1;
                break;
        }
        if (newX < 0 || newY < 0 || newX > size.getX() || newY > size.getY()) {
            return lastPosition;  // no changes to report, move is not allowed.
        }

        // now checkout if we can actually move forward within the maze
        try {
            MazeElement mazeElementAt = maze.getMazeElementAt(newX, newY);
            switch (mazeElementAt) {
                case WALL:
                    logger.error("Move not allowed; found a WALL.");
                    return new PlayerPosition(lastPosition.getDirection(),
                            lastPosition.getX(),
                            lastPosition.getY(),
                            MoveStatus.WALL_AHEAD.getStatusDescription());
                case START:
                case SPACE:
                    PlayerPosition newPosition = new PlayerPosition(lastPosition.getDirection(),
                            newX,
                            newY,
                            MoveStatus.SUCCESS.getStatusDescription());
                    mazeSession.setLastPosition(newPosition);
                    return newPosition;
                case EXIT:
                    // if we got here, boy we're done!!!!  :)
                    logger.info("and we have a winner!!! sessionId = [" + sessionId + "]");
                    SessionManager.evictSession(sessionId);
                    return new PlayerPosition(lastPosition.getDirection(),
                            lastPosition.getX(),
                            lastPosition.getY(),
                            MoveStatus.EXIT_FOUND.getStatusDescription());
            }
        } catch (MazeOutOfBoundsException e) {
            e.printStackTrace();
            logger.error("Move not allowed; out of maze coordinates.");
            return new PlayerPosition(lastPosition.getDirection(),
                    lastPosition.getX(),
                    lastPosition.getY(),
                    MoveStatus.OUT_OF_MAZE.getStatusDescription());
        }
        return lastPosition;
    }
}
