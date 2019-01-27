package pl.carshare.core.user;

@FunctionalInterface
public interface UserByIdGetter
{
  User get(Long id) throws NoSuchUserException;
}
