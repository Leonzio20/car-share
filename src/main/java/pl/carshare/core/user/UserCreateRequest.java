package pl.carshare.core.user;

import javax.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import pl.carshare.core.bean.BeanValidation;

@Setter
@Validated
public class UserCreateRequest
{
  private @NotNull String userName;
  private @NotNull CharSequence password;
  private @NotNull CharSequence repeatedPassword;

  User create(UserByUserNameFinder userByUserNameFinder, PasswordEncoder passwordEncoder) throws
    UserWithLoginAlreadyExistsException, PasswordMismatchException
  {
    BeanValidation.validate(this);

    if (userByUserNameFinder.find(userName)
      .isPresent())
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

}
