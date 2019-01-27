package pl.carshare.core;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import pl.carshare.core.user.InvalidUserNameOrPasswordException;
import pl.carshare.core.user.UserLoginRequest;
import pl.carshare.core.user.UserService;

@Component
public final class CustomAuthenticationProvider implements AuthenticationProvider
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

    try
    {
      userService.login(request);
    }
    catch (InvalidUserNameOrPasswordException exc)
    {
      throw new BadCredentialsException(exc.getMessage(), exc);
    }

    return new UsernamePasswordAuthenticationToken(userName, password,
      Collections.singletonList((GrantedAuthority) () -> "ROLE_USER"));
  }

  @Override
  public boolean supports(Class<?> authentication)
  {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}