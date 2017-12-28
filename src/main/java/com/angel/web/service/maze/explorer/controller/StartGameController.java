package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeFacilitator;
import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import com.angel.web.service.maze.explorer.domain.Maze;
import com.angel.web.service.maze.explorer.domain.MoveStatus;
import com.angel.web.service.maze.explorer.domain.PlayerDirection;
import com.angel.web.service.maze.explorer.domain.PlayerPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartGameController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MazeFacilitator mazeFacilitator;

    @RequestMapping(method = RequestMethod.PUT, value = "/start")
    public PlayerPosition startGame(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException {
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);
        Integer level = mazeSession.getLevel();
        Maze maze = mazeFacilitator.getMazeByLevel(level);
        Maze.Point startPosition = maze.getStartPosition();
        PlayerPosition playerPosition = new PlayerPosition(PlayerDirection.NORTH,
                startPosition.getX(),
                startPosition.getY(),
                MoveStatus.START.getStatusDescription());
        mazeSession.setLastPosition(playerPosition);
        return playerPosition;
    }
}
