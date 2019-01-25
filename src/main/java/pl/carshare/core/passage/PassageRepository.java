package pl.carshare.core.passage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonzio
 */
@Repository
public interface PassageRepository extends JpaRepository<Passage, Long>
{

}
