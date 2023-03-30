package treboder.optaplanner.examples.nqueens.controller;

import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.*;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import treboder.optaplanner.examples.nqueens.domain.NQueens;
import treboder.optaplanner.examples.nqueens.domain.Queen;
import treboder.optaplanner.examples.nqueens.persistence.NQueensGenerator;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/nqueens")
public class NQueensController {

    private static Logger logger = LoggerFactory.getLogger(NQueensGenerator.class);

//    @Autowired
//    private TimeTableRepository timeTableRepository;

    @Autowired
    private SolverManager<NQueens, Long> solverManager;

    @Autowired
    private ScoreManager<NQueens, HardSoftScore> scoreManager;

//    @GetMapping()
//    public TimeTable getTimeTable() {
//        // Get the solver status before loading the solution
//        // to avoid the race condition that the solver terminates between them
//        SolverStatus solverStatus = getSolverStatus();
//        TimeTable solution = timeTableRepository.findById(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
//        scoreManager.updateScore(solution); // Sets the score
//        solution.setSolverStatus(solverStatus);
//        return solution;
//    }

//    @PostMapping("/solveAndListen") // avoids HTTP timeouts much more elegantly.
//    public void solveAndListen() {
//        solverManager.solveAndListen(TimeTableRepository.SINGLETON_TIME_TABLE_ID,
//                timeTableRepository::findById,
//                timeTableRepository::save);
//    }

    @PostMapping("/solveAndWait") // waits for the solver to finish, which can still cause an HTTP timeout.
    public NQueens solveAndWait() {
        //TimeTable problem = timeTableRepository.findById(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
        NQueens problem = new NQueensGenerator().createNQueens(8);
        return solveWithNewConfig(problem);
    }

    public NQueens solveWithNewConfig(NQueens problem) {
        SolverConfig solverConfig = SolverConfig.createFromXmlResource("nqueensSolverConfig.xml");
        solverConfig.withTerminationConfig(new TerminationConfig()
                .withMinutesSpentLimit(1L)
                .withBestScoreLimit("0"));
        SolverFactory<NQueens> solverFactory = SolverFactory.create(solverConfig);
        Solver<NQueens> solver = solverFactory.buildSolver();
        NQueens solution = solver.solve(problem);
        // Display the result
        logger.info("Solved 8 queens:\n" + toDisplayString(solution));
        return solution;
    }

    public NQueens solveWithManager(NQueens problem) {
        //SolverJob<NQueens, Long> solverJob = solverManager.solve(TimeTableRepository.SINGLETON_TIME_TABLE_ID, problem);
        SolverJob<NQueens, Long> solverJob = solverManager.solve(1L, problem);
        NQueens solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        // Display the result
        logger.info("Solved 8 queens:\n" + toDisplayString(solution));
        return solution;
    }

//    public SolverStatus getSolverStatus() {
//        return solverManager.getSolverStatus(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
//    }
//
//    @PostMapping("/stopSolving")
//    public void stopSolving() {
//        solverManager.terminateEarly(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
//    }

    private static String toDisplayString(NQueens nQueens) {
        StringBuilder displayString = new StringBuilder();
        int n = nQueens.getN();
        List<Queen> queenList = nQueens.getQueenList();
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                Queen queen = queenList.get(column);
                if (queen.getColumn().getIndex() != column) {
                    throw new IllegalStateException("The queenList is not in the expected order.");
                }
                displayString.append(" ");
                if (queen.getRow() != null && queen.getRow().getIndex() == row) {
                    displayString.append("Q");
                } else {
                    displayString.append("_");
                }
            }
            displayString.append("\n");
        }
        return displayString.toString();
    }

}
