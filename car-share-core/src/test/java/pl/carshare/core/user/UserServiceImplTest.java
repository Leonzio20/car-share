package pl.carshare.core.user;

import static org.mockito.Mockito.*;

import java.util.Optional;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.carshare.core.TestConfiguration;

/**
 * @author leonzio
 */
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@ContextConfiguration(classes = {TestConfiguration.class})
class UserServiceImplTest
{
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void testCreate()
  {
    String userName = "newUser";
    char[] password = {'p', 'a', 's', 's'};

    UserCreateRequest request = new UserCreateRequest();
    request.setUserName(userName);
    request.setPassword(password);
    request.setRepeatedPassword(password);

    when(userRepository.findByUserName(same(userName))).thenReturn(Optional.empty());
    User user = mock(User.class);
    when(userRepository.save(same(user))).thenReturn(user);

    User createdUser = userService.create(request);

    verify(userRepository, atMost(1)).save(user);
    Assertions.assertEquals(userName, createdUser.getUserName());
  }

  @Test
  void testCreateUserWithUserNameExists()
  {
    String userName = "newUser";
    char[] password = {'p', 'a', 's', 's'};

    UserCreateRequest request = new UserCreateRequest();
    request.setUserName(userName);
    request.setPassword(password);
    request.setRepeatedPassword(password);

    User user = mock(User.class);
    when(userRepository.findByUserName(userName)).thenReturn(Optional.of(user));

    Assertions.assertThrows(UserWithLoginAlreadyExistsException.class, () -> userService.create(request));
  }
}