package treboder.optaplanner.examples.nqueens.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import treboder.optaplanner.examples.nqueens.domain.MyColumn;

import java.util.List;

// ToDo try CrudRepository<Column, Long>
public interface ColumnRepository extends PagingAndSortingRepository<MyColumn, Long> {

    @Override
    List<MyColumn> findAll();

}
