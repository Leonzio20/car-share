package pl.carshare.core.user;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author leonzio
 */
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserLoginRequestTest
{
  @Mock
  private UserByUserNameFinder userByUserNameFinder;

  @Mock
  private PasswordEncoder passwordEncoder;

  private UserLoginRequest request;

  @BeforeEach
  void setUp()
  {
    request = new UserLoginRequest();
  }

  @Test
  void testLoginSuccess()
  {
    String userName = "user";
    CharSequence password = "pass";

    request.setUserName(userName);
    request.setPassword(password);

    User user = mock(User.class);

    when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
    when(userByUserNameFinder.find(userName)).thenReturn(Optional.of(user));

    request.login(userByUserNameFinder, passwordEncoder);

    verify(passwordEncoder, times(1)).matches(password, user.getPassword());
    verify(userByUserNameFinder, times(1)).find(userName);
  }

  @Test
  void testLoginFails()
  {
    String userName = "user";
    CharSequence password = "pass";

    request.setUserName(userName);
    request.setPassword(password);

    when(userByUserNameFinder.find(userName)).thenReturn(Optional.empty());

    Exception exception = assertThrows(InvalidUserNameOrPasswordException.class,
      () -> request.login(userByUserNameFinder, passwordEncoder));

    assertEquals("Invalid user name or password", exception.getMessage());

    verify(userByUserNameFinder, times(1)).find(userName);
  }

  @Test
  void testLoginValidationFails()
  {
    Exception exception = assertThrows(ConstraintViolationException.class,
      () -> request.login(userByUserNameFinder, passwordEncoder));

    String exceptionMessage = exception.getMessage();
    assertThat(exceptionMessage, containsString("userName: must not be null"));
    assertThat(exceptionMessage, containsString("password: must not be null"));
  }
}