package treboder.optaplanner.examples.pixelfinder.controller;

import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import treboder.optaplanner.examples.pixelfinder.PixelfinderApplication;
import treboder.optaplanner.examples.pixelfinder.data.PixelFinderGenerator;
import treboder.optaplanner.examples.pixelfinder.domain.Pixel;
import treboder.optaplanner.examples.pixelfinder.domain.PixelFinder;

import java.util.concurrent.ExecutionException;

@RestController()
@RequestMapping("pixelfinder")
public class PixelFinderController {

    private static Logger logger = LoggerFactory.getLogger(PixelfinderApplication.class);

    //@Autowired
    //private repository;

    @Autowired
    private SolverManager<PixelFinder, Long> solverManager;

    //@Autowired
    //private ScoreManager<PixelFinder, HardSoftScore> scoreManager;

    @PostMapping("/solveWithManagerAndWait") // waits for the solver to finish, which can still cause an HTTP timeout.
    public PixelFinder PixelFindersolveWithManagerAndWait(@RequestParam(defaultValue = "20") int mapsize) {
        PixelFinder problem = new PixelFinderGenerator().createPixelFinder(mapsize);
        SolverJob<PixelFinder, Long> solverJob = solverManager.solve(1L, problem);
        PixelFinder solution;
        try { // wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }

        logger.info(solution.toString());
        solverManager.close();
        return solution;
    }

}
