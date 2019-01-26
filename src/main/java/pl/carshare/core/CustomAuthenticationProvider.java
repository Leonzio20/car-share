package pl.carshare.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.carshare.core.user.UserLoginRequest;
import pl.carshare.core.user.UserService;

/**
 * @author leonzio
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException
  {
    String userName = authentication.getName();
    String password = String.valueOf(authentication.getCredentials());

    UserLoginRequest request = new UserLoginRequest();
    request.setUserName(userName);
    request.setPassword(password);

    userService.login(request);
    return new UsernamePasswordAuthenticationToken(userName, password);
  }

  @Override
  public boolean supports(Class<?> authentication)
  {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}