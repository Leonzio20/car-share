package pl.carshare.core.user;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import pl.carshare.core.bean.BeanValidation;

/**
 * @author leonzio
 */
@Setter
@Validated
public class UserLoginRequest
{
  private @NotNull String userName;
  private @NotNull CharSequence password;

  boolean login(UserByUserNameAndPasswordFinder userByUserNameAndPasswordFinder, PasswordEncoder passwordEncoder) throws
    InvalidUserNameOrPasswordException
  {
    BeanValidation.validate(this);

    return userByUserNameAndPasswordFinder.find(userName, passwordEncoder.encode(password))
      .map(user -> true)
      .orElseThrow(InvalidUserNameOrPasswordException::new);
  }

  interface UserByUserNameAndPasswordFinder
  {
    Optional<User> find(String userName, String password);
  }
}
