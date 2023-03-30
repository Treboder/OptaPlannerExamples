package treboder.optaplanner.examples.nqueens.solver;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;
import treboder.optaplanner.examples.nqueens.domain.MyRow;
import treboder.optaplanner.examples.nqueens.domain.NQueens;

import java.util.Comparator;

// RowStrengthWeightFactory improves solver performance with domain-knowledge.
// It tells the solver which planning variables have higher impact on the solution, and should be tried with priority.
// cf. https://docs.jboss.org/optaplanner/release/7.54.0.Final/optaplanner-docs/html_single/#plannerConfiguration

public class RowStrengthWeightFactory implements SelectionSorterWeightFactory<NQueens, MyRow> {

    private static int calculateDistanceFromMiddle(int n, int columnIndex) {
        int middle = n / 2;
        int distanceFromMiddle = Math.abs(columnIndex - middle);
        if ((n % 2 == 0) && (columnIndex < middle)) {
            distanceFromMiddle--;
        }
        return distanceFromMiddle;
    }

    @Override
    public RowStrengthWeight createSorterWeight(NQueens nQueens, MyRow myRow) {
        int distanceFromMiddle = calculateDistanceFromMiddle(nQueens.getN(), myRow.getIndex());
        return new RowStrengthWeight(myRow, distanceFromMiddle);
    }

    public static class RowStrengthWeight implements Comparable<RowStrengthWeight> {

        // The stronger rows are on the side, so they have a higher distance to the middle
        private static final Comparator<RowStrengthWeight> COMPARATOR = Comparator
                .comparingInt((RowStrengthWeight weight) -> weight.distanceFromMiddle)
                .thenComparingInt(weight -> weight.myRow.getIndex());

        private final MyRow myRow;
        private final int distanceFromMiddle;

        public RowStrengthWeight(MyRow myRow, int distanceFromMiddle) {
            this.myRow = myRow;
            this.distanceFromMiddle = distanceFromMiddle;
        }

        @Override
        public int compareTo(RowStrengthWeight other) {
            return COMPARATOR.compare(this, other);
        }
    }
}
