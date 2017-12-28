package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeFacilitator;
import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import com.angel.web.service.maze.explorer.domain.CoordinatesInfo;
import com.angel.web.service.maze.explorer.domain.Maze;
import com.angel.web.service.maze.explorer.domain.MazeElement;
import com.angel.web.service.maze.explorer.domain.MazeOutOfBoundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoordinatesInfoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MazeFacilitator mazeFacilitator;

    @RequestMapping(method = RequestMethod.GET, value = "/coordinates")
    public CoordinatesInfo getCoordinatesInfo(@RequestParam(value = "sessionId") Long sessionId,
                                              @RequestParam(value = "x", defaultValue = "0") Integer x,
                                              @RequestParam(value = "y", defaultValue = "0") Integer y) throws ExplorerSessionException {
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);

        Integer level = mazeSession.getLevel();
        Maze mazeByLevel = mazeFacilitator.getMazeByLevel(level);
        MazeElement mazeElementAt = null;
        try {
            mazeElementAt = mazeByLevel.getMazeElementAt(x, y);
        } catch (MazeOutOfBoundsException e) {
            e.printStackTrace();
            //TODO exception handling here
        }

        CoordinatesInfo coordinatesInfo = new CoordinatesInfo(x,y, mazeElementAt);
        return coordinatesInfo;
    }

}
