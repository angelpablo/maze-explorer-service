package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovementHistoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = "/movements-history")
    public MazeSession getMovementsHistory(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException {
        if (CotrollerUtils.isInvalidSession(sessionId)) {
            logger.error("Bad sessionId: [" + sessionId +"]");
            throw new ExplorerSessionException("No active session");
        }

        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);
        if (mazeSession == null) {
            logger.error("Bad session, bad boy.: [" + sessionId +"]");
            throw new ExplorerSessionException("No active session");
        }
        return mazeSession;
    }
}
