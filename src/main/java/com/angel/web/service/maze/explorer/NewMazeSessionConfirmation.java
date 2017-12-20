package com.angel.web.service.maze.explorer;

import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.domain.MazeDescription;

public class NewMazeSessionConfirmation {
    private final MazeSession mazeSession;
    private final MazeDescription mazeDescription;

    public NewMazeSessionConfirmation(MazeSession mazeSession, MazeDescription mazeDescription) {
        this.mazeSession = mazeSession;
        this.mazeDescription = mazeDescription;
    }

    public MazeSession getMazeSession() {
        return mazeSession;
    }

    public MazeDescription getMazeDescription() {
        return mazeDescription;
    }
}
