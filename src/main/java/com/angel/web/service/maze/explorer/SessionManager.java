package com.angel.web.service.maze.explorer;

import com.angel.web.service.maze.explorer.controller.ExplorerSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SessionManager {
    private final static Logger logger = LoggerFactory.getLogger(SessionManager.class);
    private static final Map<Long, MazeSession> sessionContainer = new ConcurrentHashMap<>();

    private SessionManager() {

    }

    public static void storeSession(MazeSession mazeSession) {
        Long sessionId = mazeSession.getSessionId();
        MazeSession alreadyStoredMazeSession = sessionContainer.get(sessionId);
        if (alreadyStoredMazeSession != null) {
            logger.warn("Traying to store duplicate session: [" + sessionId + "]");
            return;
        }

        sessionContainer.put(sessionId, mazeSession);
    }

    public static MazeSession getMazeSession(Long sessionId) throws ExplorerSessionException {
        MazeSession mazeSession = sessionContainer.get(sessionId);
        if (mazeSession == null) {
            logger.error("Bad sessionId: [" + sessionId +"]");
            throw new ExplorerSessionException("No active session");
        }
        return mazeSession;
    }

    public static void evictSession(Long sessionId) {
        sessionContainer.remove(sessionId);
    }

}
