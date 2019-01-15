package pl.carshare.core.user;

import lombok.Setter;

/**
 * @author leonzio
 */
@Setter
public class UserCreateRequest
{
  private String userName;
  private String password;
  private String repeatedPassword;

  User create()
  {
    User user = new User();
    user.setUserName(userName);
    user.setPassword(password);
    return user;
  }
}
