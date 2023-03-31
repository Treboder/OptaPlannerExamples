package treboder.optaplanner.examples.nqueens.score;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import treboder.optaplanner.examples.nqueens.domain.Queen;

import static org.optaplanner.core.api.score.stream.Joiners.equal;

public class NQueensConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[] {
                horizontalConflict(factory),
                ascendingDiagonalConflict(factory),
                descendingDiagonalConflict(factory),
        };
    }

    // ************************************************************************
    // Hard constraints
    // ************************************************************************

    public Constraint horizontalConflict(ConstraintFactory factory) {
        return factory.forEachUniquePair(Queen.class, equal(Queen::getRowIndex))
                .penalize(SimpleScore.ONE)
                .asConstraint("Horizontal conflict");
    }

    public Constraint ascendingDiagonalConflict(ConstraintFactory factory) {
        return factory.forEachUniquePair(Queen.class, equal(Queen::getAscendingDiagonalIndex))
                .penalize(SimpleScore.ONE)
                .asConstraint("Ascending diagonal conflict");
    }

    public Constraint descendingDiagonalConflict(ConstraintFactory factory) {
        return factory.forEachUniquePair(Queen.class, equal(Queen::getDescendingDiagonalIndex))
                .penalize(SimpleScore.ONE)
                .asConstraint("Descending diagonal conflict");
    }

}
