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
import treboder.optaplanner.examples.nqueens.persistence.NQueensRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/nqueens")
public class NQueensController {

    private static Logger logger = LoggerFactory.getLogger(NQueensGenerator.class);

    @Autowired
    private NQueensRepository nQueensRepository;

    @Autowired
    private SolverManager<NQueens, Long> solverManager;

    @Autowired
    private ScoreManager<NQueens, HardSoftScore> scoreManager;

    @PostMapping("/solveWithNewConfigAndWait") // waits for the solver to finish, which can still cause an HTTP timeout.
    public NQueens solveWithNewConfigAndWait() {
        //TimeTable problem = timeTableRepository.findById(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
        NQueens problem = new NQueensGenerator().createNQueens(8);
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

    @PostMapping("/solveWithManagerAndWait") // waits for the solver to finish, which can still cause an HTTP timeout.
    public NQueens solveWithManagerAndWait() {
        NQueens problem = new NQueensGenerator().createNQueens(8);
        SolverJob<NQueens, Long> solverJob = solverManager.solve(nQueensRepository.SINGLETON_NQUEENS_ID, problem);
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

    @PostMapping("/solveAndListen") // avoids HTTP timeouts much more elegantly.
    public void solveAndListen() {
        NQueens problem = new NQueensGenerator().createNQueens(8);
        nQueensRepository.save(problem);
        solverManager.solveAndListen(nQueensRepository.SINGLETON_NQUEENS_ID,
                nQueensRepository::findById,
                nQueensRepository::save);
    }

    @GetMapping()
    public NQueens getNQueens() {
        // Get the solver status before loading the solution to avoid the race condition that the solver terminates between them
        SolverStatus solverStatus = solverManager.getSolverStatus(nQueensRepository.SINGLETON_NQUEENS_ID);
        NQueens solution = nQueensRepository.findById(nQueensRepository.SINGLETON_NQUEENS_ID);
        scoreManager.updateScore(solution); // Sets the score
        solution.setSolverStatus(solverStatus);
        return solution;
    }

    @PostMapping("/stopSolving")
    public void stopSolving() {
        solverManager.terminateEarly(nQueensRepository.SINGLETON_NQUEENS_ID);
    }

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
