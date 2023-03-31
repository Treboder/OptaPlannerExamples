package treboder.optaplanner.examples.nqueens;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import treboder.optaplanner.examples.nqueens.controller.NQueensController;
import treboder.optaplanner.examples.nqueens.domain.NQueens;
import treboder.optaplanner.examples.nqueens.domain.Queen;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NQueensControllerTest {

    @Autowired
    NQueensController nQueensController;



    @Test
    @Order(1)
    // @Timeout(600_000) // test fails if its execution time exceeds a given duration
    public void solveWithWithManagerAndWait() throws InterruptedException {
        nQueensController.createNQueens(8);
        NQueens nQueens = nQueensController.solveWithManagerAndWait();
        for (Queen queen : nQueens.getQueenList()) {
            assertNotNull(queen.getRow());
        }
        assertTrue(nQueens.getScore().isFeasible());
    }

    @Test
    @Order(2)
    // @Timeout(600_000) // test fails if its execution time exceeds a given duration
    public void solveWithNewConfigAndWait() throws InterruptedException {
        nQueensController.createNQueens(8);
        NQueens nQueens = nQueensController.solveWithNewConfigAndWait();
        for (Queen queen : nQueens.getQueenList()) {
            assertNotNull(queen.getRow());
        }
        assertTrue(nQueens.getScore().isFeasible());
    }

    @Test
    @Order(3)
    // @Timeout(600_000) // test fails if its execution time exceeds a given duration
    public void testSolveAndListen() throws InterruptedException {
        nQueensController.createNQueens(8);
        nQueensController.solveAndListen();
        NQueens nQueens = nQueensController.getNQueens();
        while (nQueens.getSolverStatus() != SolverStatus.NOT_SOLVING) {
            // Quick polling (not a Test Thread Sleep anti-pattern)
            // Test is still fast on fast machines and doesn't randomly fail on slow machines.
            Thread.sleep(20L);
            nQueens = nQueensController.getNQueens();
        }
        for (Queen queen : nQueens.getQueenList()) {
            assertNotNull(queen.getRow());
        }
        assertTrue(nQueens.getScore().isFeasible());
    }

}
