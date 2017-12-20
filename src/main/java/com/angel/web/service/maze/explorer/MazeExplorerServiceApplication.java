package com.angel.web.service.maze.explorer;

import com.angel.web.service.maze.explorer.domain.Maze;
import com.angel.web.service.maze.explorer.validator.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class MazeExplorerServiceApplication {
	private final static Logger logger = LoggerFactory.getLogger(MazeExplorerServiceApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(MazeExplorerServiceApplication.class, args);
		logger.info("Server is up and running");
	}

	// These are the maze validators used by the MazeFacilitator
    // The order her is important.
    @Bean
    MazeValidator createShapeValidator() {
        IsItRectangularMazeValidator validator = new IsItRectangularMazeValidator();
        return validator;
    }
	@Bean
	MazeValidator createAllElementsValidator() {
		ContainsAllElementsValidator validator = new ContainsAllElementsValidator();
		return validator;
	}

	@Bean
	MazeValidator createOnlyOneExitPointValidator() {
		HasOnlyOneExitPointValidator validator = new HasOnlyOneExitPointValidator();
		return validator;
	}

	@Bean
	MazeValidator createOnlyOneStartPointValidator() {
		HasOnlyOneStartPointValidator validator = new HasOnlyOneStartPointValidator();
		return validator;
	}

	// the MazeFacilitator raw material coming up
	// this guy can later be replaced by one that gets the mazes from another service
	// thru http, jms, RPC, Sockets,.....For now it will get it from local file system
	@Bean
	Map<Integer, Maze> createInMemoryMazes(@Value("${maze.file.repository.path}") String path) {
		logger.info("using file system to obtain mazes from: [" + path + "]");
		FileSystemMazeCollector collector = new FileSystemMazeCollector(path);

		return collector.getMazeCollection();
	}
}
