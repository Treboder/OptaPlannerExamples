package treboder.optaplanner.examples.nqueens.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import treboder.optaplanner.examples.nqueens.domain.MyRow;

import java.util.List;

// ToDo try CrudRepository<Row, Long>
public interface RowRepository extends PagingAndSortingRepository<MyRow, Long> {

    @Override
    List<MyRow> findAll();

}
