package pl.carshare.core.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonzio
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
  Optional<User> findByUserName(String userName);

  Optional<User> findByUserNameAndPassword(String userName, String password);
}
