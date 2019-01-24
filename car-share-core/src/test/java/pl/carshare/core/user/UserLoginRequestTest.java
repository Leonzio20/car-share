package pl.carshare.core.user;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
  private UserLoginRequest.UserByUserNameAndPasswordFinder userByUserNameAndPasswordFinder;

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
    String encodedPassword = "encoded";

    request.setUserName(userName);
    request.setPassword(password);

    User user = mock(User.class);

    when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
    when(userByUserNameAndPasswordFinder.find(userName, encodedPassword)).thenReturn(Optional.of(user));

    boolean loginResult = request.login(userByUserNameAndPasswordFinder, passwordEncoder);

    assertTrue(loginResult);

    verify(passwordEncoder, times(1)).encode(password);
    verify(userByUserNameAndPasswordFinder, times(1)).find(userName, encodedPassword);
  }

  @Test
  void testLoginFails()
  {
    String userName = "user";
    CharSequence password = "pass";
    String encodedPassword = "encoded";

    request.setUserName(userName);
    request.setPassword(password);

    when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
    when(userByUserNameAndPasswordFinder.find(userName, encodedPassword)).thenReturn(Optional.empty());

    Exception exception = assertThrows(InvalidUserNameOrPasswordException.class,
      () -> request.login(userByUserNameAndPasswordFinder, passwordEncoder));

    assertEquals("Invalid user name or password", exception.getMessage());

    verify(passwordEncoder, times(1)).encode(password);
    verify(userByUserNameAndPasswordFinder, times(1)).find(userName, encodedPassword);
  }

  @Test
  void testLoginValidationFails()
  {
    Exception exception = assertThrows(ConstraintViolationException.class,
      () -> request.login(userByUserNameAndPasswordFinder, passwordEncoder));

    String exceptionMessage = exception.getMessage();
    assertThat(exceptionMessage, containsString("userName: must not be null"));
    assertThat(exceptionMessage, containsString("password: must not be null"));
  }
}