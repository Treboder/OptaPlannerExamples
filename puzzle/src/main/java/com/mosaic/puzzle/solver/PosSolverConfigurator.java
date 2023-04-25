package com.mosaic.puzzle.solver;

import com.mosaic.puzzle.domain.PuzzleSolution;
import com.mosaic.puzzle.domain.PuzzleTile;
import com.mosaic.puzzle.score.PuzzleEasyScoreCalculator;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicType;
import org.optaplanner.core.config.constructionheuristic.decider.forager.ConstructionHeuristicForagerConfig;
import org.optaplanner.core.config.constructionheuristic.decider.forager.ConstructionHeuristicPickEarlyType;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

public class PosSolverConfigurator {

    private Long timeSecondsSpentLimit = 10L;

    public Solver<PuzzleSolution> createSolverFromXML() {
        SolverConfig solverConfig = SolverConfig.createFromXmlResource("solverConfig.xml");
        SolverFactory<PuzzleSolution> solverFactory = SolverFactory.create(solverConfig);
        Solver<PuzzleSolution> solver = solverFactory.buildSolver();
        return solver;
    }

    public Solver<PuzzleSolution> createSolverWithAPI() {
        SolverConfig solverConfig = new SolverConfig();
        solverConfig.withSolutionClass(PuzzleSolution.class);
        solverConfig.withEntityClasses(PuzzleTile.class);
        solverConfig.withEasyScoreCalculatorClass(PuzzleEasyScoreCalculator.class);
        // define search phases
        solverConfig.withPhases(
                new ConstructionHeuristicPhaseConfig()
                        .withConstructionHeuristicType(ConstructionHeuristicType.FIRST_FIT)
                        .withForagerConfig(createConstructionForeagerConfig()),
                new LocalSearchPhaseConfig());
        // set termination config
        solverConfig.withTerminationConfig(new TerminationConfig()
                        .withSecondsSpentLimit(timeSecondsSpentLimit)
                        .withBestScoreLimit("0hard/0soft"));
        // create solver and return
        SolverFactory<PuzzleSolution> solverFactory = SolverFactory.create(solverConfig);
        Solver<PuzzleSolution> solver = solverFactory.buildSolver();
        return solver;
    }

    private static ConstructionHeuristicForagerConfig createConstructionForeagerConfig() {
        ConstructionHeuristicForagerConfig foreAger = new ConstructionHeuristicForagerConfig();
        foreAger.setPickEarlyType(ConstructionHeuristicPickEarlyType.FIRST_FEASIBLE_SCORE);
        //foreAger.setPickEarlyType(ConstructionHeuristicPickEarlyType.FIRST_NON_DETERIORATING_SCORE);
        return foreAger;
    }


}
