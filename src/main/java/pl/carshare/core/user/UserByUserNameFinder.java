package pl.carshare.core.user;

import java.util.Optional;

@FunctionalInterface
interface UserByUserNameFinder
{
  Optional<User> find(String userName);
}
