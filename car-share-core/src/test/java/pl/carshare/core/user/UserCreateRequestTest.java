package pl.carshare.core.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
class UserCreateRequestTest
{
  @Mock
  private UserCreateRequest.UserByLoginFinder userByLoginFinder;

  @Mock
  private PasswordEncoder passwordEncoder;

  private UserCreateRequest createRequest;

  @BeforeEach
  void setUp()
  {
    createRequest = new UserCreateRequest();
  }

  @Test
  void testCreateSuccess()
  {
    String userName = "userName";
    CharSequence password = "pass";
    String encodedPassword = "encoded";

    createRequest.setUserName(userName);
    createRequest.setPassword(password);
    createRequest.setRepeatedPassword(password);

    when(userByLoginFinder.find(userName)).thenReturn(Optional.empty());
    when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

    User createdUser = createRequest.create(userByLoginFinder, passwordEncoder);

    assertNotNull(createdUser);
    assertEquals(userName, createdUser.getUserName());
    assertEquals(encodedPassword, createdUser.getPassword());

    verify(userByLoginFinder, times(1)).find(userName);
    verify(passwordEncoder, times(1)).encode(password);
  }

  @Test
  void testCreateUserWithLoginExists()
  {
    String userName = "userName";
    CharSequence password = "pass";

    createRequest.setUserName(userName);
    createRequest.setPassword(password);
    createRequest.setRepeatedPassword(password);

    User user = mock(User.class);
    when(userByLoginFinder.find(userName)).thenReturn(Optional.of(user));

    Exception exception = assertThrows(UserWithLoginAlreadyExistsException.class,
      () -> createRequest.create(userByLoginFinder, passwordEncoder));

    assertEquals("User with login '" + userName + "' already exists", exception.getMessage());

    verify(userByLoginFinder, times(1)).find(userName);
  }

  @Test
  void testCreateDifferentPassword()
  {
    String userName = "userName";
    CharSequence password = "pass";
    CharSequence repeatedPassword = "repeatedPassword";

    createRequest.setUserName(userName);
    createRequest.setPassword(password);
    createRequest.setRepeatedPassword(repeatedPassword);

    when(userByLoginFinder.find(userName)).thenReturn(Optional.empty());

    Exception exception = assertThrows(PasswordMismatchException.class,
      () -> createRequest.create(userByLoginFinder, passwordEncoder));

    assertEquals("Password and repeated password does not match!", exception.getMessage());

    verify(userByLoginFinder, times(1)).find(userName);
  }
}