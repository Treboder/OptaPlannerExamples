package treboder.optaplanner.examples.nqueens.controller;



import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import treboder.optaplanner.examples.nqueens.domain.NQueens;
import treboder.optaplanner.examples.nqueens.persistence.NQueensGenerator;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/nqueens")
public class NQueensController {

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
        return solve(problem);
    }

    public NQueens solve(NQueens problem) {
        //SolverJob<NQueens, Long> solverJob = solverManager.solve(TimeTableRepository.SINGLETON_TIME_TABLE_ID, problem);
        SolverJob<NQueens, Long> solverJob = solverManager.solve(1L, problem);
        NQueens solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
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

}
