package com.angel.web.service.maze.explorer.controller;

import com.angel.web.service.maze.explorer.MazeSession;
import com.angel.web.service.maze.explorer.SessionManager;
import com.angel.web.service.maze.explorer.domain.MoveStatus;
import com.angel.web.service.maze.explorer.domain.PlayerDirection;
import com.angel.web.service.maze.explorer.domain.PlayerPosition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangeDirectionController {

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-north")
    public PlayerPosition changePlayerDirectionNorth(@RequestParam(value = "sessionId") Long sessionId) {
        return changeDirectionTo(PlayerDirection.NORTH, sessionId);    }

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-south")
    public PlayerPosition changePlayerDirectionSouth(@RequestParam(value = "sessionId") Long sessionId) {
        return changeDirectionTo(PlayerDirection.SOUTH, sessionId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-east")
    public PlayerPosition changePlayerDirectionEast(@RequestParam(value = "sessionId") Long sessionId) {
        return changeDirectionTo(PlayerDirection.EAST, sessionId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/change-direction-west")
    public PlayerPosition changePlayerDirectionWest(@RequestParam(value = "sessionId") Long sessionId) {
        return changeDirectionTo(PlayerDirection.WEST, sessionId);
    }

    private PlayerPosition changeDirectionTo(PlayerDirection direction, Long sessionId) {
        MazeSession mazeSession = SessionManager.getMazeSession(sessionId);
        PlayerPosition lastPosition = mazeSession.getLastPosition();
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
