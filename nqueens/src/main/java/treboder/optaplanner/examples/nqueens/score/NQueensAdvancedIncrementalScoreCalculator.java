//package treboder.optaplanner.examples.nqueens.score;
//
//import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
//import org.optaplanner.core.api.score.calculator.IncrementalScoreCalculator;
//import treboder.optaplanner.examples.nqueens.domain.NQueens;
//import treboder.optaplanner.examples.nqueens.domain.Queen;
//import treboder.optaplanner.examples.nqueens.domain.Row;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///** advanced implementation of incremental scoring based on the map based scoring implementation,
// *  cf. NQueensMapBasedEasyScoreCalculator
// */
//
//public class NQueensAdvancedIncrementalScoreCalculator implements IncrementalScoreCalculator<NQueens, SimpleScore> {
//
//    // cf. NQueensMapBasedEasyScoreCalculator
//    private Map<Integer, List<Queen>> rowIndexMap;
//    private Map<Integer, List<Queen>> ascendingDiagonalIndexMap;
//    private Map<Integer, List<Queen>> descendingDiagonalIndexMap;
//
//    private int score;
//
//    @Override
//    public void resetWorkingSolution(NQueens nQueens) {
//        int n = nQueens.getN();
//        rowIndexMap = new HashMap<>(n);
//        ascendingDiagonalIndexMap = new HashMap<>(n * 2);
//        descendingDiagonalIndexMap = new HashMap<>(n * 2);
//        for (int i = 0; i < n; i++) {
//            rowIndexMap.put(i, new ArrayList<>(n));
//            ascendingDiagonalIndexMap.put(i, new ArrayList<>(n));
//            descendingDiagonalIndexMap.put(i, new ArrayList<>(n));
//            if (i != 0) {
//                ascendingDiagonalIndexMap.put(n - 1 + i, new ArrayList<>(n));
//                descendingDiagonalIndexMap.put((-i), new ArrayList<>(n));
//            }
//        }
//        score = 0;
//        for (Queen queen : nQueens.getQueenList()) {
//            insert(queen);
//        }
//    }
//
//    @Override
//    public void beforeEntityAdded(Object entity) {
//        // Do nothing
//    }
//
//    @Override
//    public void afterEntityAdded(Object entity) {
//        insert((Queen) entity);
//    }
//
//    @Override
//    public void beforeVariableChanged(Object entity, String variableName) {
//        retract((Queen) entity);
//    }
//
//    @Override
//    public void afterVariableChanged(Object entity, String variableName) {
//        insert((Queen) entity);
//    }
//
//    @Override
//    public void beforeEntityRemoved(Object entity) {
//        retract((Queen) entity);
//    }
//
//    @Override
//    public void afterEntityRemoved(Object entity) {
//        // Do nothing
//    }
//
//    // update the maps with newly added queen
//    private void insert(Queen queen) {
//        Row row = queen.getRow();
//        if (row != null) {
//            int rowIndex = queen.getRowIndex();
//            List<Queen> rowIndexList = rowIndexMap.get(rowIndex);
//            score -= rowIndexList.size();
//            rowIndexList.add(queen);
//            List<Queen> ascendingDiagonalIndexList = ascendingDiagonalIndexMap.get(queen.getAscendingDiagonalIndex());
//            score -= ascendingDiagonalIndexList.size();
//            ascendingDiagonalIndexList.add(queen);
//            List<Queen> descendingDiagonalIndexList = descendingDiagonalIndexMap.get(queen.getDescendingDiagonalIndex());
//            score -= descendingDiagonalIndexList.size();
//            descendingDiagonalIndexList.add(queen);
//        }
//    }
//
//    // update the maps with newly removed queen
//    private void retract(Queen queen) {
//        Row row = queen.getRow();
//        if (row != null) {
//            List<Queen> rowIndexList = rowIndexMap.get(queen.getRowIndex());
//            rowIndexList.remove(queen);
//            score += rowIndexList.size();
//            List<Queen> ascendingDiagonalIndexList = ascendingDiagonalIndexMap.get(queen.getAscendingDiagonalIndex());
//            ascendingDiagonalIndexList.remove(queen);
//            score += ascendingDiagonalIndexList.size();
//            List<Queen> descendingDiagonalIndexList = descendingDiagonalIndexMap.get(queen.getDescendingDiagonalIndex());
//            descendingDiagonalIndexList.remove(queen);
//            score += descendingDiagonalIndexList.size();
//        }
//    }
//
//    @Override
//    public SimpleScore calculateScore() {
//        return SimpleScore.of(score);
//    }
//
//}
