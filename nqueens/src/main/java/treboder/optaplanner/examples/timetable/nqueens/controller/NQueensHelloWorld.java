package treboder.optaplanner.examples.timetable.nqueens.controller;


import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import treboder.optaplanner.examples.timetable.nqueens.domain.NQueens;
import treboder.optaplanner.examples.timetable.nqueens.domain.Queen;
import treboder.optaplanner.examples.timetable.nqueens.persistence.NQueensGenerator;

import java.util.List;

public class NQueensHelloWorld {

    public static void main(String[] args) {
        // Build the Solver
        SolverFactory<NQueens> solverFactory = SolverFactory.createFromXmlResource("nqueensSolverConfig.xml");
        Solver<NQueens> solver = solverFactory.buildSolver();

        // Load a problem with 8 queens
        NQueens unsolved8Queens = new NQueensGenerator().createNQueens(8);

        // Solve the problem
        NQueens solved8Queens = solver.solve(unsolved8Queens);

        // Display the result
        System.out.println("\nSolved 8 queens:\n" + toDisplayString(solved8Queens));
    }

    public static String toDisplayString(NQueens nQueens) {
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
