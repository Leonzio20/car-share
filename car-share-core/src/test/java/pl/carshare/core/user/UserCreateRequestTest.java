package pl.carshare.core.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
class UserCreateRequestTest
{
  @Mock
  private UserCreateRequest.UserByLoginFinder userByLoginFinder;

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
    char[] password = new char[]{'p', 'a', 's', 's'};

    createRequest.setUserName(userName);
    createRequest.setPassword(password);
    createRequest.setRepeatedPassword(password);

    when(userByLoginFinder.find(userName)).thenReturn(Optional.empty());

    User createdUser = createRequest.create(userByLoginFinder);

    assertNotNull(createdUser);
    assertEquals(userName, createdUser.getUserName());
    assertEquals(Arrays.toString(password), createdUser.getPassword());
  }

  @Test
  void testCreateUserWithLoginExists()
  {
    String userName = "userName";
    char[] password = new char[]{'p', 'a', 's', 's'};

    createRequest.setUserName(userName);
    createRequest.setPassword(password);
    createRequest.setRepeatedPassword(password);

    User user = mock(User.class);
    when(userByLoginFinder.find(userName)).thenReturn(Optional.of(user));

    Exception exception = assertThrows(UserWithLoginAlreadyExistsException.class,
      () -> createRequest.create(userByLoginFinder));

    assertEquals("User with login '" + userName + "' already exists", exception.getMessage());
  }

  @Test
  void testCreateDifferentPassword()
  {
    String userName = "userName";
    char[] password = new char[]{'p', 'a', 's', 's'};
    char[] repeatedPassword = new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};

    createRequest.setUserName(userName);
    createRequest.setPassword(password);
    createRequest.setRepeatedPassword(repeatedPassword);

    when(userByLoginFinder.find(userName)).thenReturn(Optional.empty());

    Exception exception = assertThrows(PasswordMismatchException.class, () -> createRequest.create(userByLoginFinder));

    assertEquals("Password and repeated password does not match!", exception.getMessage());
  }
}