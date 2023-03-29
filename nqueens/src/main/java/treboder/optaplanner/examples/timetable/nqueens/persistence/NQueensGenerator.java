package treboder.optaplanner.examples.timetable.nqueens.persistence;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import treboder.optaplanner.examples.timetable.nqueens.domain.Column;
import treboder.optaplanner.examples.timetable.nqueens.domain.NQueens;
import treboder.optaplanner.examples.timetable.nqueens.domain.Queen;
import treboder.optaplanner.examples.timetable.nqueens.domain.Row;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NQueensGenerator {

    private static Logger logger = LoggerFactory.getLogger(NQueensGenerator.class);

    public NQueensGenerator() {

    }

    public NQueens createNQueens(int n) {
        NQueens nQueens = new NQueens(0L);
        nQueens.setN(n);
        nQueens.setColumnList(createColumnList(nQueens));
        nQueens.setRowList(createRowList(nQueens));
        nQueens.setQueenList(createQueenList(nQueens));
        BigInteger possibleSolutionSize = BigInteger.valueOf(nQueens.getN()).pow(nQueens.getN());
        logger.info("NQueens {} has {} queens with a search space of {}.",
                n, nQueens.getN()
                //,AbstractSolutionImporter.getFlooredPossibleSolutionSize(possibleSolutionSize)
                );
        return nQueens;
    }

    private List<Column> createColumnList(NQueens nQueens) {
        int n = nQueens.getN();
        List<Column> columnList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Column column = new Column(i);
            columnList.add(column);
        }
        return columnList;
    }

    private List<Row> createRowList(NQueens nQueens) {
        int n = nQueens.getN();
        List<Row> rowList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Row row = new Row(i);
            rowList.add(row);
        }
        return rowList;
    }

    private List<Queen> createQueenList(NQueens nQueens) {
        int n = nQueens.getN();
        List<Queen> queenList = new ArrayList<>(n);
        long id = 0;
        for (Column column : nQueens.getColumnList()) {
            Queen queen = new Queen(id);
            id++;
            queen.setColumn(column);
            // Notice that we leave the PlanningVariable properties on null
            queenList.add(queen);
        }
        return queenList;
    }

}
