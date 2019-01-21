package pl.carshare.core.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.carshare.core.TestConfiguration;

/**
 * @author leonzio
 */
@ExtendWith({ SpringExtension.class, MockitoExtension.class })
@ContextConfiguration(classes = { TestConfiguration.class })
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceImplTest
{
  @MockBean
  private UserRepository userRepository;

  @Autowired
  private UserServiceImpl userService;

  @Test
  void testCreateSuccess()
  {
    String userName = "newUser";
    char[] password = { 'p', 'a', 's', 's' };

    UserCreateRequest request = new UserCreateRequest();
    request.setUserName(userName);
    request.setPassword(password);
    request.setRepeatedPassword(password);

    given(userRepository.findByUserName(userName)).willReturn(Optional.empty());

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    User user = mock(User.class);
    given(userRepository.save(userArgumentCaptor.capture())).willReturn(user);

    User createdUser = userService.create(request);

    verify(userRepository, times(1)).findByUserName(userName);
    verify(userRepository, times(1)).save(userArgumentCaptor.capture());

    assertNotNull(createdUser);
    assertEquals(createdUser, user);
  }

  @Test
  void testCreateUserWithUserNameExists()
  {
    String userName = "newUser";
    char[] password = { 'p', 'a', 's', 's' };

    UserCreateRequest request = new UserCreateRequest();
    request.setUserName(userName);
    request.setPassword(password);
    request.setRepeatedPassword(password);

    User user = mock(User.class);
    when(userRepository.findByUserName(userName)).thenReturn(Optional.of(user));

    Exception exception = assertThrows(UserWithLoginAlreadyExistsException.class, () -> userService.create(request));

    verify(userRepository, times(1)).findByUserName(userName);

    assertEquals("User with login '" + userName + "' already exists", exception.getMessage());
  }

  @Test
  void testLoginSuccess()
  {
    String userName = "userName";
    char[] password = new char[]{ 'p', 'a', 's', 's' };

    UserLoginRequest request = new UserLoginRequest();
    request.setUserName(userName);
    request.setPassword(password);

    User user = mock(User.class);
    when(userRepository.findByUserNameAndPassword(userName, Arrays.toString(password))).thenReturn(Optional.of(user));

    boolean loginResult = userService.login(request);

    verify(userRepository, times(1)).findByUserNameAndPassword(userName, Arrays.toString(password));

    assertTrue(loginResult);
  }

  @Test
  void testLoginFails()
  {
    String userName = "userName";
    char[] password = new char[]{ 'p', 'a', 's', 's' };

    UserLoginRequest request = new UserLoginRequest();
    request.setUserName(userName);
    request.setPassword(password);

    when(userRepository.findByUserNameAndPassword(userName, Arrays.toString(password))).thenReturn(Optional.empty());

    Exception exception = assertThrows(InvalidUserNameOrPasswordException.class, () -> userService.login(request));

    verify(userRepository, times(1)).findByUserNameAndPassword(userName, Arrays.toString(password));

    assertEquals("Invalid user name or password", exception.getMessage());
  }

}