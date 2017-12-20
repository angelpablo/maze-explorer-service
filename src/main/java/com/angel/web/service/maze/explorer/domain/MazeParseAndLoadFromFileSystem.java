package com.angel.web.service.maze.explorer.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class MazeParseAndLoadFromFileSystem {
    private final static Logger logger = LoggerFactory.getLogger(MazeParseAndLoadFromFileSystem.class);
    public static void parseAndLoadMaze(Path path, Map<Integer, Maze> mazeCollection) {
        try {
            logger.info("Proceeding to parse and load maze file: [" + path.getFileName().toString() + "]");
            String[] fileNameParts = path.getFileName().toString().split("\\.");
            Integer level = Integer.valueOf(fileNameParts[0]);
            Maze alreadyStoredMazeForGivenLevel = mazeCollection.get(level);
            if (alreadyStoredMazeForGivenLevel != null) {
                logger.warn("Duplicate level rejected: [" + path.getFileName() + "]");
                return;
            }

            logger.info(Arrays.toString(fileNameParts));
            List<String> linesOfElements = Files.readAllLines(path);
            char[][] maze = new char[linesOfElements.size()][];
            int mazeLines = 0;
            for (String element : linesOfElements) {
                char[] mazeLine = element.toCharArray();
                maze[mazeLines++] = mazeLine;
            }
            logger.info("Parsed file: [" + path.getFileName().toString() + "] --> " + Arrays.deepToString(maze));
            logger.info("Storing in memory level: [" + level + "] --> " + Arrays.deepToString(maze));
            mazeCollection.put(level, new Maze(maze));
            return;
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
            logger.info("excluding file file: [" + path.getFileName() + "]");
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            logger.info("file violates naming convention: [" + path.getFileName() + "]. Skipping");
        }

        return;
    }
}
