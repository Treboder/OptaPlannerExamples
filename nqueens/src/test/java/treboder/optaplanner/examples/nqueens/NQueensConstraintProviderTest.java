package treboder.optaplanner.examples.nqueens;

import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import treboder.optaplanner.examples.nqueens.domain.MyColumn;
import treboder.optaplanner.examples.nqueens.domain.MyRow;
import treboder.optaplanner.examples.nqueens.domain.NQueens;
import treboder.optaplanner.examples.nqueens.domain.Queen;
import treboder.optaplanner.examples.nqueens.score.NQueensConstraintProvider;

import java.time.DayOfWeek;
import java.time.LocalTime;

@SpringBootTest
public class NQueensConstraintProviderTest {

    @Autowired
    ConstraintVerifier<NQueensConstraintProvider, NQueens> constraintVerifier;

    @Test
    void horizontalConflict() {
        Queen  queen1 = new Queen(1L, new MyRow(1), new MyColumn(1));
        Queen  queen2 = new Queen(2L, new MyRow(1), new MyColumn(2));
        constraintVerifier.verifyThat(NQueensConstraintProvider::horizontalConflict)
                .given(queen1, queen2)
                .penalizesBy(1);
    }

    @Test
    void descendingDiagonalConflict() {
        Queen  queen1 = new Queen(1L, new MyRow(1), new MyColumn(1));
        Queen  queen2 = new Queen(2L, new MyRow(2), new MyColumn(2));
        constraintVerifier.verifyThat(NQueensConstraintProvider::descendingDiagonalConflict)
                .given(queen1, queen2)
                .penalizesBy(1);
    }

    @Test
    void ascendingDiagonalConflict() {
        Queen  queen1 = new Queen(1L, new MyRow(2), new MyColumn(2));
        Queen  queen2 = new Queen(2L, new MyRow(1), new MyColumn(3));
        constraintVerifier.verifyThat(NQueensConstraintProvider::ascendingDiagonalConflict)
                .given(queen1, queen2)
                .penalizesBy(1);
    }





}
