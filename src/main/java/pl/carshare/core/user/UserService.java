package pl.carshare.core.user;

public interface UserService
{
  User create(UserCreateRequest request) throws UserWithLoginAlreadyExistsException, PasswordMismatchException;

  void login(UserLoginRequest request) throws InvalidUserNameOrPasswordException;

  User getById(Long id) throws NoSuchUserException;
}
