package treboder.optaplanner.examples.nqueens.data;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import treboder.optaplanner.examples.nqueens.domain.MyColumn;
import treboder.optaplanner.examples.nqueens.domain.Queen;
import treboder.optaplanner.examples.nqueens.domain.MyRow;
import treboder.optaplanner.examples.nqueens.domain.NQueens;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NQueensGenerator {

    // There is only one problem instance, so there is only obe nqueensID
    public static final Long SINGLETON_NQUEENS_ID = 1L;

    private static Logger logger = LoggerFactory.getLogger(NQueensGenerator.class);

    public NQueensGenerator() {

    }

    public NQueens createNQueens(int n) {
        NQueens nQueens = new NQueens(SINGLETON_NQUEENS_ID);
        nQueens.setN(n);
        nQueens.setColumnList(createColumnList(nQueens));
        nQueens.setRowList(createRowList(nQueens));
        nQueens.setQueenList(createQueenList(nQueens));
        BigInteger possibleSolutionSize = BigInteger.valueOf(nQueens.getN()).pow(nQueens.getN());
        logger.info("NQueens {} has {} queens with a search space of {}.",
                n, nQueens.getN(), getFlooredPossibleSolutionSize(possibleSolutionSize));
        return nQueens;
    }

    private List<MyColumn> createColumnList(NQueens nQueens) {
        int n = nQueens.getN();
        List<MyColumn> myColumnList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            MyColumn myColumn = new MyColumn(i);
            myColumnList.add(myColumn);
        }
        return myColumnList;
    }

    private List<MyRow> createRowList(NQueens nQueens) {
        int n = nQueens.getN();
        List<MyRow> myRowList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            MyRow myRow = new MyRow(i);
            myRowList.add(myRow);
        }
        return myRowList;
    }

    private List<Queen> createQueenList(NQueens nQueens) {
        int n = nQueens.getN();
        List<Queen> queenList = new ArrayList<>(n);
        long id = 0;
        for (MyColumn myColumn : nQueens.getColumnList()) {
            Queen queen = new Queen(id);
            id++;
            queen.setColumn(myColumn);
            // Notice that we leave the PlanningVariable properties on null
            queenList.add(queen);
        }
        return queenList;
    }

    private static String getFlooredPossibleSolutionSize(BigInteger possibleSolutionSize) {
        if (possibleSolutionSize == null) {
            return null;
        }
        if (possibleSolutionSize.compareTo(BigInteger.valueOf(1000L)) < 0) {
            return possibleSolutionSize.toString();
        }
        BigDecimal possibleSolutionSizeBigDecimal = new BigDecimal(possibleSolutionSize);
        int decimalDigits = possibleSolutionSizeBigDecimal.scale() < 0
                ? possibleSolutionSizeBigDecimal.precision() - possibleSolutionSizeBigDecimal.scale()
                : possibleSolutionSizeBigDecimal.precision();
        return "10^" + decimalDigits;
    }
}
