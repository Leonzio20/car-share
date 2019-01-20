package pl.carshare.core.user;

/**
 * @author leonzio
 */
public class UserWithLoginAlreadyExistsException extends RuntimeException
{
  private UserWithLoginAlreadyExistsException(String message)
  {
    super(message);
  }

  static UserWithLoginAlreadyExistsException of(String userName)
  {
    String message = String.format("User with login '%s' already exists", userName);
    return new UserWithLoginAlreadyExistsException(message);
  }
}
