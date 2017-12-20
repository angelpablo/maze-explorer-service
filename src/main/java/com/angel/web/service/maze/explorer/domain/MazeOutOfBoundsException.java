package com.angel.web.service.maze.explorer.domain;

public class MazeOutOfBoundsException extends Exception {
    public MazeOutOfBoundsException() {
    }

    public MazeOutOfBoundsException(String message) {
        super(message);
    }

    public MazeOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MazeOutOfBoundsException(Throwable cause) {
        super(cause);
    }

    public MazeOutOfBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
