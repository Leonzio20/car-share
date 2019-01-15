package pl.carshare.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonzio
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
}
