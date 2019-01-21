package pl.carshare.core.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author leonzio
 */
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserLoginRequestTest
{
  @Mock
  private UserLoginRequest.UserByUserNameAndPasswordFinder userByUserNameAndPasswordFinder;

  private UserLoginRequest loginRequest;

  @BeforeEach
  void setUp()
  {
    loginRequest = new UserLoginRequest();
  }

  @Test
  void testLoginSuccess()
  {
    String userName = "user";
    char[] password = new char[]{'p', 'a', 's', 's'};

    loginRequest.setUserName(userName);
    loginRequest.setPassword(password);

    User user = mock(User.class);

    when(userByUserNameAndPasswordFinder.find(userName, Arrays.toString(password))).thenReturn(
      Optional.of(user));

    boolean loginResult = loginRequest.login(userByUserNameAndPasswordFinder);

    assertTrue(loginResult);
  }

  @Test
  void testLoginFails()
  {
    String userName = "user";
    char[] password = new char[]{'p', 'a', 's', 's'};

    loginRequest.setUserName(userName);
    loginRequest.setPassword(password);

    when(userByUserNameAndPasswordFinder.find(userName, Arrays.toString(password))).thenReturn(
      Optional.empty());

    Exception exception = assertThrows(InvalidUserNameOrPasswordException.class,
      () -> loginRequest.login(userByUserNameAndPasswordFinder));

    assertEquals("Invalid user name or password", exception.getMessage());
  }
}