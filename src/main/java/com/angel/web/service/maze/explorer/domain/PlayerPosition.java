package com.angel.web.service.maze.explorer.domain;

public class PlayerPosition {
    private final PlayerDirection direction;
    private final int x;
    private final int y;
    private final String status;

    public PlayerPosition(PlayerDirection direction, int x, int y, String status) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public PlayerDirection getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getStatus() {
        return status;
    }
}
