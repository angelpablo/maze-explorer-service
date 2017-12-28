package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import com.angel.web.service.maze.explorer.domain.MoveStatus;
import com.angel.web.service.maze.explorer.domain.PlayerDirection;
import com.angel.web.service.maze.explorer.domain.PlayerPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChangeDirectionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-north")
    public PlayerPosition changePlayerDirectionNorth(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException, GameNotStartedException {
        return changeDirectionTo(PlayerDirection.NORTH, sessionId);    }

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-south")
    public PlayerPosition changePlayerDirectionSouth(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException, GameNotStartedException {
        return changeDirectionTo(PlayerDirection.SOUTH, sessionId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-east")
    public PlayerPosition changePlayerDirectionEast(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException, GameNotStartedException {
        return changeDirectionTo(PlayerDirection.EAST, sessionId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-west")
    public PlayerPosition changePlayerDirectionWest(@RequestParam(value = "sessionId") Long sessionId) throws ExplorerSessionException, GameNotStartedException {
        return changeDirectionTo(PlayerDirection.WEST, sessionId);
    }

    private PlayerPosition changeDirectionTo(PlayerDirection direction, Long sessionId) throws ExplorerSessionException, GameNotStartedException {
        if (CotrollerUtils.isInvalidSession(sessionId)) {
            logger.error("Bad sessionId: [" + sessionId +"]");
            throw new ExplorerSessionException("No active session");
        }

        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);
        if (mazeSession == null) {
            logger.error("Possibly expired sessionId: [" + sessionId +"]");
            throw new ExplorerSessionException("No active session");
        }

        PlayerPosition lastPosition = mazeSession.getLastPosition();
        if (lastPosition == null) {
            logger.error("Attempt to change direction on a non-started game: [sessionId=" + sessionId + "]");
            throw new GameNotStartedException("No active game");
        }
        if (lastPosition.getDirection().equals(direction)) {
            return lastPosition;
        }
        PlayerPosition newPosition = new PlayerPosition(direction,
                lastPosition.getX(),
                lastPosition.getY(),
                MoveStatus.DIRECTION_CHANGED.getStatusDescription());
        mazeSession.setLastPosition(newPosition);
        return newPosition;

    }
}
