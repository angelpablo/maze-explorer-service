package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeFacilitator;
import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import com.angel.web.service.maze.explorer.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhatIsInFrontController {
    @Autowired
    private MazeFacilitator mazeFacilitator;

    @RequestMapping(method = RequestMethod.GET, value = "/what-is-infront")
    public String getWhatIsInFront(@RequestParam(value = "sessionId") Long sessionId) {
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);
        Integer level = mazeSession.getLevel();
        Maze maze = mazeFacilitator.getMazeByLevel(level);
        Maze.Point size = maze.getSize();

        PlayerPosition lastPosition = mazeSession.getLastPosition();
        int newX = 0;
        int newY = 0;

        PlayerDirection direction = lastPosition.getDirection();
        switch (direction) {
            case NORTH:
                newX = lastPosition.getX();
                newY = lastPosition.getY() + 1;
                break;
            case SOUTH:
                newX = lastPosition.getX();
                newY = lastPosition.getY() -1;
                break;
            case EAST:
                newX = lastPosition.getX() + 1;
                newY = lastPosition.getY();
                break;
            case WEST:
                newX = lastPosition.getX() -1;
                newY = lastPosition.getY();
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
