package com.angel.web.service.maze.explorer.controller;

public class GameNotStartedException extends Exception {
    public GameNotStartedException() {
    }

    public GameNotStartedException(String message) {
        super(message);
    }

    public GameNotStartedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameNotStartedException(Throwable cause) {
        super(cause);
    }

    public GameNotStartedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
