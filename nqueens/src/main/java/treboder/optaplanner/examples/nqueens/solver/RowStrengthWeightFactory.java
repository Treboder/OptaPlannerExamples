package treboder.optaplanner.examples.nqueens.solver;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;
import treboder.optaplanner.examples.nqueens.domain.Row;
import treboder.optaplanner.examples.nqueens.domain.NQueens;

import java.util.Comparator;

public class RowStrengthWeightFactory implements SelectionSorterWeightFactory<NQueens, Row> {

    private static int calculateDistanceFromMiddle(int n, int columnIndex) {
        int middle = n / 2;
        int distanceFromMiddle = Math.abs(columnIndex - middle);
        if ((n % 2 == 0) && (columnIndex < middle)) {
            distanceFromMiddle--;
        }
        return distanceFromMiddle;
    }

    @Override
    public RowStrengthWeight createSorterWeight(NQueens nQueens, Row row) {
        int distanceFromMiddle = calculateDistanceFromMiddle(nQueens.getN(), row.getIndex());
        return new RowStrengthWeight(row, distanceFromMiddle);
    }

    public static class RowStrengthWeight implements Comparable<RowStrengthWeight> {

        // The stronger rows are on the side, so they have a higher distance to the middle
        private static final Comparator<RowStrengthWeight> COMPARATOR = Comparator
                .comparingInt((RowStrengthWeight weight) -> weight.distanceFromMiddle)
                .thenComparingInt(weight -> weight.row.getIndex());

        private final Row row;
        private final int distanceFromMiddle;

        public RowStrengthWeight(Row row, int distanceFromMiddle) {
            this.row = row;
            this.distanceFromMiddle = distanceFromMiddle;
        }

        @Override
        public int compareTo(RowStrengthWeight other) {
            return COMPARATOR.compare(this, other);
        }
    }
}
