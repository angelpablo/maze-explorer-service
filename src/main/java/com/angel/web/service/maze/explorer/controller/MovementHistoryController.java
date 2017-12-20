package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovementHistoryController {
    @RequestMapping(method = RequestMethod.GET, value = "/movements-history")
    public MazeSession getMovementsHistory(@RequestParam(value = "sessionId") Long sessionId) {
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);
        return mazeSession;
    }
}
