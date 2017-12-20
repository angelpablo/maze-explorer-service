package com.angel.web.service.maze.explorer;

import com.angel.web.service.maze.explorer.domain.Maze;
import com.angel.web.service.maze.explorer.validator.MazeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MazeFacilitator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private List<MazeValidator> mazeValidators;

    @Autowired
    private Map<Integer, Maze> mazes;

    @PostConstruct
    public void initialization() {
        logger.info("Post-construction coming up...");
        for (Integer level: mazes.keySet()) {
            Maze maze = mazes.get(level);
            for (MazeValidator validator: mazeValidators) {
                if (!validator.isValid(maze.getMaze())) {
                    logger.warn("Discarding level: [" + level + "] as it doesn't pass validation process.");
                    mazes.remove(level);
                }
            }
        }
        logger.info("Post-construction finished.");
    }

    public Maze getMazeByLevel(Integer level) {
        return mazes.get(level);
    }
}
