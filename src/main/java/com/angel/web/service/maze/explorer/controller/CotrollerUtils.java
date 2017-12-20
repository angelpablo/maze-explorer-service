package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.domain.CoordinatesInfo;
import com.angel.web.service.maze.explorer.domain.MoveStatus;
import com.angel.web.service.maze.explorer.domain.PlayerPosition;

public final class CotrollerUtils {
    private CotrollerUtils() {

    }

    public static PlayerPosition createNoSessionPlayerPosition() {
        PlayerPosition playerPosition = new PlayerPosition(null, -1, -1, "Invalid session");
        return playerPosition;
    }

    public static CoordinatesInfo createNoSessionCoordinatesInfo() {
        CoordinatesInfo coordinatesInfo = new CoordinatesInfo(-1,-1, null);
        return coordinatesInfo;
    }

    public static PlayerPosition createNoGameStartedPlayerPosition() {
        PlayerPosition playerPosition = new PlayerPosition(null, -1, -1, MoveStatus.NO_GAME_STARTED.getStatusDescription());
        return playerPosition;
    }

    public static String createNoSessionString() {
        return "Invalid Session.";
    }

    public static boolean isInvalidSession(Long sessionId) {
        return (sessionId == null || sessionId.compareTo(Long.valueOf(0)) == 0);
    }

    public static String createNoGameString() {
        return "No game started.";
    }
}
