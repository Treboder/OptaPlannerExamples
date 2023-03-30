//package treboder.optaplanner.examples.nqueens.score;
//
//import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
//import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;
//import treboder.optaplanner.examples.nqueens.domain.NQueens;
//import treboder.optaplanner.examples.nqueens.domain.Queen;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class NQueensMapBasedEasyScoreCalculator implements EasyScoreCalculator<NQueens, SimpleScore> {
//
//    /** iterate over all queens and count occurrences for all rows and diagonals
//     * the score map represents the occurrences of queens per row and diagonal
//     */
//
//    @Override
//    public SimpleScore calculateScore(NQueens nQueens) {
//        int n = nQueens.getN();
//        List<Queen> queenList = nQueens.getQueenList();
//
//        Map<Integer, Integer> rowIndexCountMap = new HashMap<>(n);
//        Map<Integer, Integer> ascendingDiagonalIndexCountMap = new HashMap<>(n);
//        Map<Integer, Integer> descendingDiagonalIndexCountMap = new HashMap<>(n);
//        int score = 0;
//        for (Queen queen : queenList) {
//            if (queen.getRow() != null) {
//                int rowIndex = queen.getRowIndex();
//                Integer rowIndexCount = rowIndexCountMap.get(rowIndex);
//                if (rowIndexCount != null) {
//                    score -= rowIndexCount;
//                    rowIndexCount++;
//                } else {
//                    rowIndexCount = 1;
//                }
//                rowIndexCountMap.put(rowIndex, rowIndexCount);
//
//                int ascendingDiagonalIndex = queen.getAscendingDiagonalIndex();
//                Integer ascendingDiagonalIndexCount = ascendingDiagonalIndexCountMap.get(ascendingDiagonalIndex);
//                if (ascendingDiagonalIndexCount != null) {
//                    score -= ascendingDiagonalIndexCount;
//                    ascendingDiagonalIndexCount++;
//                } else {
//                    ascendingDiagonalIndexCount = 1;
//                }
//                ascendingDiagonalIndexCountMap.put(ascendingDiagonalIndex, ascendingDiagonalIndexCount);
//
//                int descendingDiagonalIndex = queen.getDescendingDiagonalIndex();
//                Integer descendingDiagonalIndexCount = descendingDiagonalIndexCountMap.get(descendingDiagonalIndex);
//                if (descendingDiagonalIndexCount != null) {
//                    score -= descendingDiagonalIndexCount;
//                    descendingDiagonalIndexCount++;
//                } else {
//                    descendingDiagonalIndexCount = 1;
//                }
//                descendingDiagonalIndexCountMap.put(descendingDiagonalIndex, descendingDiagonalIndexCount);
//            }
//        }
//        return SimpleScore.of(score);
//    }
//
//}
