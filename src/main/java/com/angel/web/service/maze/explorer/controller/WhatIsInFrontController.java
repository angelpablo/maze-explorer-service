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

@RestController
public class WhatIsInFrontController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MazeFacilitator mazeFacilitator;

    @RequestMapping(method = RequestMethod.GET, value = "/what-is-infront")
    public String getWhatIsInFront(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException, GameNotStartedException {
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);

        PlayerPosition lastPosition = mazeSession.getLastPosition();
        if (lastPosition == null) {
            logger.error("Attempt to look-up element in-front on a non-started game: [sessionId=" + sessionId + "]");
            throw new GameNotStartedException("No active game");
        }

        Integer level = mazeSession.getLevel();
        Maze maze = mazeFacilitator.getMazeByLevel(level);
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
            return "Nothing";
        }

        // now checkout if we can actually move forward within the maze
        try {
            MazeElement mazeElementAt = maze.getMazeElementAt(newX, newY);
            switch (mazeElementAt) {
                case WALL:
                    return MazeElement.WALL.name();
                case START:
                    return MazeElement.START.name();
                case SPACE:
                    return MazeElement.SPACE.name();
                case EXIT:
                    return MazeElement.EXIT.name();
            }
        } catch (MazeOutOfBoundsException e) {
            return "Nothing";
        }
        return "Emptyness";
    }

}
