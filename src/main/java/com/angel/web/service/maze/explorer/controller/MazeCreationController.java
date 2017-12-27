package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeFacilitator;
import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.NewMazeSessionConfirmation;
import com.angel.web.service.maze.explorer.SessionManager;
import com.angel.web.service.maze.explorer.domain.Maze;
import com.angel.web.service.maze.explorer.domain.MazeDescription;
import com.angel.web.service.maze.explorer.domain.MazeDescriptionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MazeCreationController {
    private static final AtomicLong sessionCounter = new AtomicLong(0);

    @Autowired
    private MazeFacilitator mazeFacilitator;

    @RequestMapping(method = RequestMethod.POST, value = "/maze")
    public NewMazeSessionConfirmation createNewMaze(@RequestParam(value = "level", defaultValue = "1") Integer level) {
        Maze maze = mazeFacilitator.getMazeByLevel(level);
        if (maze == null) {
            // TODO funky level requested.
        }
        MazeDescription mazeDescription = MazeDescriptionGenerator.getMazeDescription(maze, level);

        MazeSession session = new MazeSession(level, sessionCounter.incrementAndGet());
        session.setPlayerMovesSizeBasedOnMazeSize(maze);
        SessionManager.storeSession(session);

        NewMazeSessionConfirmation confirmation = new NewMazeSessionConfirmation(session, mazeDescription);
        return confirmation;
    }
}
