package pl.carshare.core.user;

import java.util.Optional;

import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author leonzio
 */
@Setter
public class UserLoginRequest
{
  private String userName;
  private CharSequence password;

  boolean login(UserByUserNameAndPasswordFinder userByUserNameAndPasswordFinder, PasswordEncoder passwordEncoder) throws
    InvalidUserNameOrPasswordException
  {
    return userByUserNameAndPasswordFinder.find(userName, passwordEncoder.encode(password))
      .map(user -> true)
      .orElseThrow(InvalidUserNameOrPasswordException::new);
  }

  interface UserByUserNameAndPasswordFinder
  {
    Optional<User> find(String userName, String password);
  }
}
