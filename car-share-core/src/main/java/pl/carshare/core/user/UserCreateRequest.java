package pl.carshare.core.user;

import java.util.Optional;

import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author leonzio
 */
@Setter
public class UserCreateRequest
{
  private String userName;
  private CharSequence password;
  private CharSequence repeatedPassword;

  User create(UserByLoginFinder userByLoginFinder, PasswordEncoder passwordEncoder) throws
    UserWithLoginAlreadyExistsException, PasswordMismatchException
  {
    if (userByLoginFinder.find(userName).isPresent())
    {
      throw UserWithLoginAlreadyExistsException.of(userName);
    }

    if (!password.equals(repeatedPassword))
    {
      throw new PasswordMismatchException();
    }

    User user = new User();
    user.setUserName(userName);
    user.setPassword(passwordEncoder.encode(password));
    return user;
  }

  @FunctionalInterface
  interface UserByLoginFinder
  {
    Optional<User> find(String userName);
  }
}
