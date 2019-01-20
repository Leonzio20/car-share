package pl.carshare.core.user;

import java.util.Arrays;
import java.util.Optional;

import lombok.Setter;

/**
 * @author leonzio
 */
@Setter
public class UserLoginRequest
{
  private String userName;
  private char[] password;

  boolean login(UserByUserNameAndPasswordFinder userByUserNameAndPasswordFinder) throws
    InvalidUserNameOrPasswordException
  {
    return userByUserNameAndPasswordFinder.find(userName, Arrays.toString(password))
      .map(user -> true)
      .orElseThrow(InvalidUserNameOrPasswordException::new);
  }

  interface UserByUserNameAndPasswordFinder
  {
    Optional<User> find(String userName, String password);
  }
}
