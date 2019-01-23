package pl.carshare.core.user;

/**
 * @author leonzio
 */
@FunctionalInterface
public interface UserByIdGetter
{
  User get(Long id) throws NoSuchUserException;
}
