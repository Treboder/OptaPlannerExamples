package treboder.optaplanner.examples.nqueens.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import treboder.optaplanner.examples.nqueens.domain.Queen;

import java.util.List;

// ToDo try CrudRepository<Queen, Long>
public interface QueenRepository extends PagingAndSortingRepository<Queen, Long>  {

    @Override
    List<Queen> findAll();

}
