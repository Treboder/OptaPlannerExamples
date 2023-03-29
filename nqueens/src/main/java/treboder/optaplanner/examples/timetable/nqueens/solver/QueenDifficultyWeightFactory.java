package treboder.optaplanner.examples.timetable.nqueens.solver;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;
import treboder.optaplanner.examples.timetable.nqueens.domain.NQueens;
import treboder.optaplanner.examples.timetable.nqueens.domain.Queen;


import java.util.Comparator;

public class QueenDifficultyWeightFactory implements SelectionSorterWeightFactory<NQueens, Queen> {

    private static int calculateDistanceFromMiddle(int n, int columnIndex) {
        int middle = n / 2;
        int distanceFromMiddle = Math.abs(columnIndex - middle);
        if ((n % 2 == 0) && (columnIndex < middle)) {
            distanceFromMiddle--;
        }
        return distanceFromMiddle;
    }

    @Override
    public QueenDifficultyWeight createSorterWeight(NQueens nQueens, Queen queen) {
        int distanceFromMiddle = calculateDistanceFromMiddle(nQueens.getN(), queen.getColumnIndex());
        return new QueenDifficultyWeight(queen, distanceFromMiddle);
    }

    public static class QueenDifficultyWeight implements Comparable<QueenDifficultyWeight> {

        // The more difficult queens have a lower distance to the middle
        private static final Comparator<QueenDifficultyWeight> COMPARATOR = Comparator
                .comparingInt((QueenDifficultyWeight weight) -> -weight.distanceFromMiddle) // Decreasing.
                .thenComparingInt(weight -> weight.queen.getColumnIndex()); // Tie-breaker.

        private final Queen queen;
        private final int distanceFromMiddle;

        public QueenDifficultyWeight(Queen queen, int distanceFromMiddle) {
            this.queen = queen;
            this.distanceFromMiddle = distanceFromMiddle;
        }

        @Override
        public int compareTo(QueenDifficultyWeight other) {
            return COMPARATOR.compare(this, other);
        }
    }
}
