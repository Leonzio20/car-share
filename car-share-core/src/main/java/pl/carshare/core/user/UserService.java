package pl.carshare.core.user;

/**
 * @author radziejoski
 **/
public interface UserService
{
  User create(UserCreateRequest request) throws UserWithLoginAlreadyExistsException, PasswordMismatchException;

  boolean login(UserLoginRequest request) throws InvalidUserNameOrPasswordException;
}
