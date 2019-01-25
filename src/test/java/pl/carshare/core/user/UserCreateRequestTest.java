package pl.carshare.core.user;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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
class UserCreateRequestTest
{
  @Mock
  private UserCreateRequest.UserByUserNameFinder userByUserNameFinder;

  @Mock
  private PasswordEncoder passwordEncoder;

  private UserCreateRequest request;

  @BeforeEach
  void setUp()
  {
    request = new UserCreateRequest();
  }

  @Test
  void testCreateSuccess()
  {
    String userName = "userName";
    CharSequence password = "pass";
    String encodedPassword = "encoded";

    request.setUserName(userName);
    request.setPassword(password);
    request.setRepeatedPassword(password);

    when(userByUserNameFinder.find(userName)).thenReturn(Optional.empty());
    when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

    User createdUser = request.create(userByUserNameFinder, passwordEncoder);

    assertNotNull(createdUser);
    assertEquals(userName, createdUser.getUserName());
    assertEquals(encodedPassword, createdUser.getPassword());

    verify(userByUserNameFinder, times(1)).find(userName);
    verify(passwordEncoder, times(1)).encode(password);
  }

  @Test
  void testCreateUserWithLoginExists()
  {
    String userName = "userName";
    CharSequence password = "pass";

    request.setUserName(userName);
    request.setPassword(password);
    request.setRepeatedPassword(password);

    User user = mock(User.class);
    when(userByUserNameFinder.find(userName)).thenReturn(Optional.of(user));

    Exception exception = assertThrows(UserWithLoginAlreadyExistsException.class,
      () -> request.create(userByUserNameFinder, passwordEncoder));

    assertEquals("User with login '" + userName + "' already exists", exception.getMessage());

    verify(userByUserNameFinder, times(1)).find(userName);
  }

  @Test
  void testCreateDifferentPassword()
  {
    String userName = "userName";
    CharSequence password = "pass";
    CharSequence repeatedPassword = "repeatedPassword";

    request.setUserName(userName);
    request.setPassword(password);
    request.setRepeatedPassword(repeatedPassword);

    when(userByUserNameFinder.find(userName)).thenReturn(Optional.empty());

    Exception exception = assertThrows(PasswordMismatchException.class,
      () -> request.create(userByUserNameFinder, passwordEncoder));

    assertEquals("Password and repeated password does not match!", exception.getMessage());

    verify(userByUserNameFinder, times(1)).find(userName);
  }

  @Test
  void testCreateValidationFails()
  {
    Exception exception = assertThrows(ConstraintViolationException.class,
      () -> request.create(userByUserNameFinder, passwordEncoder));

    String exceptionMessage = exception.getMessage();
    assertThat(exceptionMessage, containsString("repeatedPassword: must not be null"));
    assertThat(exceptionMessage, containsString("userName: must not be null"));
    assertThat(exceptionMessage, containsString("password: must not be null"));
  }
}