package treboder.optaplanner.examples.nqueens.persistence;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import treboder.optaplanner.examples.nqueens.domain.NQueens;

@Service
@Transactional
public class NQueensRepository {

    // There is only one problem instance, so there is only obe nqueensID
    public static final Long SINGLETON_NQUEENS_ID = 1L;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private QueenRepository queenRepository;

    public NQueens findById(Long id) {
        if (!SINGLETON_NQUEENS_ID.equals(id)) {
            throw new IllegalStateException("There is no nqueens with id (" + id + ").");
        }
        // ToDo update comment
        // Occurs in a single transaction, so each initialized lesson references the same timeslot/room instance
        // that is contained by the timeTable's timeslotList/roomList.
        return new NQueens(SINGLETON_NQUEENS_ID,
                rowRepository.findAll(),
                columnRepository.findAll(),
                queenRepository.findAll());
    }

    public void save(NQueens nqueens) {
        rowRepository.saveAll(nqueens.getRowList());
        columnRepository.saveAll(nqueens.getColumnList());
        queenRepository.saveAll(nqueens.getQueenList());
    }

    public void delete() {
        rowRepository.deleteAll();
        columnRepository.deleteAll();
        queenRepository.deleteAll();
    }

}

