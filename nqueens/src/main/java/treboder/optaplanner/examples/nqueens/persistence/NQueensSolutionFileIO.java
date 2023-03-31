package treboder.optaplanner.examples.nqueens.persistence;

import treboder.optaplanner.examples.nqueens.domain.NQueens;

public class NQueensSolutionFileIO extends AbstractJsonSolutionFileIO<NQueens> {

    public NQueensSolutionFileIO() {
        super(NQueens.class);
    }
}
