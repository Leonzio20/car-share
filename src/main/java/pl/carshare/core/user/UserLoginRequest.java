package pl.carshare.core.user;

import javax.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import pl.carshare.core.bean.BeanValidation;

@Setter
@Validated
public class UserLoginRequest
{
  private @NotNull String userName;
  private @NotNull CharSequence password;

  void login(UserByUserNameFinder userByUserNameAndPasswordFinder, PasswordEncoder passwordEncoder) throws
    InvalidUserNameOrPasswordException
  {
    BeanValidation.validate(this);

    User foundUser = userByUserNameAndPasswordFinder.find(userName)
      .orElseThrow(InvalidUserNameOrPasswordException::new);
    if (!passwordEncoder.matches(password, foundUser.getPassword()))
    {
      throw new InvalidUserNameOrPasswordException();
    }
  }
}
