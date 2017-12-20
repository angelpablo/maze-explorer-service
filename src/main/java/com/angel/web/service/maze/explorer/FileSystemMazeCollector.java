package com.angel.web.service.maze.explorer;

import com.angel.web.service.maze.explorer.domain.Maze;
import com.angel.web.service.maze.explorer.domain.MazeParseAndLoadFromFileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class will collect all possible mazes from Fise system.  This guy can
 * be replaced by a more sophisticated one(s) capable of getting them from DB,
 * other service(s).
 */
public class FileSystemMazeCollector {
    private final String path;

    public FileSystemMazeCollector(String path) {
        this.path = path;
    }

    Map<Integer, Maze> getMazeCollection() {
        Map<Integer, Maze> mazes = new HashMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile).forEach(mazeFile -> MazeParseAndLoadFromFileSystem.parseAndLoadMaze(mazeFile, mazes));
        } catch (IOException e) {
            //TODO create a hardcoded maze here so we won't fail if environment
            // fails on us
        }
        return mazes;
    }
}
