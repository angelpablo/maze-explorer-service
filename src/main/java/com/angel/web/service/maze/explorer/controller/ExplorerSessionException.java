package com.angel.web.service.maze.explorer.controller;

public class ExplorerSessionException extends Exception {
    public ExplorerSessionException() {
    }

    public ExplorerSessionException(String message) {
        super(message);
    }

    public ExplorerSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExplorerSessionException(Throwable cause) {
        super(cause);
    }

    public ExplorerSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
