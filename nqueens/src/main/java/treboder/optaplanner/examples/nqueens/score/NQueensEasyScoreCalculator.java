//package treboder.optaplanner.examples.nqueens.score;
//
//import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
//import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;
//import treboder.optaplanner.examples.nqueens.domain.NQueens;
//import treboder.optaplanner.examples.nqueens.domain.Queen;
//
//import java.util.List;
//
//public class NQueensEasyScoreCalculator implements EasyScoreCalculator<NQueens, SimpleScore> {
//
//    /** iterate over all unique queen pairs and compare their row and diagonal indices
//     * reduce the score in case of matching indices, meaning two queens share the same row or diagonal
//     */
//
//    @Override
//    public SimpleScore calculateScore(NQueens nQueens) {
//        int n = nQueens.getN();
//        List<Queen> queenList = nQueens.getQueenList();
//
//        int score = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                Queen leftQueen = queenList.get(i);
//                Queen rightQueen = queenList.get(j);
//                if (leftQueen.getRow() != null && rightQueen.getRow() != null) {
//                    if (leftQueen.getRowIndex() == rightQueen.getRowIndex()) {
//                        score--;
//                    }
//                    if (leftQueen.getAscendingDiagonalIndex() == rightQueen.getAscendingDiagonalIndex()) {
//                        score--;
//                    }
//                    if (leftQueen.getDescendingDiagonalIndex() == rightQueen.getDescendingDiagonalIndex()) {
//                        score--;
//                    }
//                }
//            }
//        }
//        return SimpleScore.of(score);
//    }
//
//}
