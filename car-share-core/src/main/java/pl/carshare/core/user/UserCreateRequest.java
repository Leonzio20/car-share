package pl.carshare.core.user;

import java.util.Arrays;
import java.util.Optional;

import lombok.Setter;

/**
 * @author leonzio
 */
@Setter
public class UserCreateRequest
{
  private String userName;
  private char[] password;
  private char[] repeatedPassword;

  User create(UserByLoginFinder userByLoginFinder) throws UserWithLoginAlreadyExistsException, PasswordMismatchException
  {
    if (userByLoginFinder.find(userName).isPresent())
    {
      throw UserWithLoginAlreadyExistsException.of(userName);
    }

    if (!Arrays.equals(password, repeatedPassword))
    {
      throw new PasswordMismatchException();
    }

    User user = new User();
    user.setUserName(userName);
    user.setPassword(Arrays.toString(password));
    return user;
  }

  @FunctionalInterface
  interface UserByLoginFinder
  {
    Optional<User> find(String userName);
  }
}
