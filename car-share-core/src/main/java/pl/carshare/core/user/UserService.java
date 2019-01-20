package pl.carshare.core.user;

/**
 * @author radziejoski
 **/
public interface UserService
{
  User create(UserCreateRequest request);

  boolean login(UserLoginRequest request);
}
