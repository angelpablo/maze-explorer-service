package com.angel.web.service.maze.explorer.domain;

public enum MoveStatus {
    WALL_AHEAD("Error, Wall ahead; no move executed."),
    SUCCESS("Move Succesful"),
    START("At starting point"),
    EXIT_FOUND("End game; exit found"),
    DIRECTION_CHANGED("Success, direction changed."),
    NO_GAME_STARTED("Error, you need to start game first."),
    OUT_OF_MAZE("Error, coordinates don't exist.");

    private String statusDescription;

    MoveStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}
