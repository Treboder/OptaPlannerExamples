package com.mosaic.puzzle;

import com.mosaic.puzzle.service.PuzzleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PuzzleApplication {

	public static void main(String[] args) {
		PuzzleService service = new PuzzleService();
		service.solveWithConfigFromAPI(10);
		SpringApplication.run(PuzzleApplication.class, args);
	}

}
