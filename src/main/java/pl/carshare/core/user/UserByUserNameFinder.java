package pl.carshare.core.user;

import java.util.Optional;

/**
 * @author leonzio
 */
@FunctionalInterface
interface UserByUserNameFinder
{
  Optional<User> find(String userName);
}
