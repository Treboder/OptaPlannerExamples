package com.mosaic.puzzle.service;

import com.mosaic.puzzle.PuzzleApplication;
import com.mosaic.puzzle.data.PuzzleDataGenerator;
import com.mosaic.puzzle.domain.PuzzleSolution;
import com.mosaic.puzzle.solver.PosSolverConfigurator;
import org.optaplanner.core.api.solver.Solver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service
public class PuzzleService {

    private static Logger logger = LoggerFactory.getLogger(PuzzleApplication.class);

    // waits for the solver to finish, which can still cause an HTTP timeout.
    public PuzzleSolution solveWithConfigFromXML(int mapsize) {
        PuzzleSolution problem = new PuzzleDataGenerator().createRandomPosSolution(mapsize);
        Solver<PuzzleSolution> solver = new PosSolverConfigurator().createSolverFromXML();
        return doSolve(problem, solver);
    }

    // waits for the solver to finish, which can still cause an HTTP timeout.
    public PuzzleSolution solveWithConfigFromAPI(int mapsize) {
        PuzzleSolution problem = new PuzzleDataGenerator().createRandomPosSolution(mapsize);
        Solver<PuzzleSolution> solver = new PosSolverConfigurator().createSolverWithAPI();
        return doSolve(problem, solver);
    }

    private PuzzleSolution doSolve(PuzzleSolution problem, Solver<PuzzleSolution> solver) {
        logger.info("Canvas initialized \n" + problem.getCanvas().toLoggerCanvas());
        //SolverJob<PosSolution, Long> solverJob = solverManager.solve(1L, problem);
        PuzzleSolution solution;
        try { // wait until the solving ends
            solution = solver.solve(problem);
        } catch (Exception e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        logger.info("Solving finished with {} and score = {}", solution.toString(), solution.getScore());
        logger.info("Solving finished with following canvas \n" + problem.getCanvas().toLoggerCanvas());
        logger.info("Solving finished with following tiles  \n" + solution.toLoggerTiles());
        logger.info("Solving finished with following deltas \n" + solution.toLoggerDeltas());
        return solution;
    }
}
