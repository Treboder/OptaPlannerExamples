package com.mosaic.puzzle;

import com.mosaic.puzzle.domain.PuzzleSolution;
import com.mosaic.puzzle.domain.PuzzleTile;
import com.mosaic.puzzle.service.PuzzleService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PositioningApplicationTests {

	@Test
	@Order(1)
	void contextLoads() {
	}

	@Test
	@Order(2)
	// @Timeout(600_000) // test fails if its execution time exceeds a given duration
	public void solveWithConfigFromAPI() {
		PuzzleService posService = new PuzzleService();
		PuzzleSolution solution = posService.solveWithConfigFromAPI(10);
		for (PuzzleTile tile : solution.getTileList()) {
			assertNotNull(tile.getX());
			assertNotNull(tile.getY());
		}
		assertTrue(solution.getScore().isFeasible());
	}

}

