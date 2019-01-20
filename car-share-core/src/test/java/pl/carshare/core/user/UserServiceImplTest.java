package pl.carshare.core.user;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author leonzio
 */
@ExtendWith({ MockitoExtension.class})
//@ContextConfiguration(classes = {UserServiceImpl.class})
//@DataJpaTest
//@EnableAutoConfiguration
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImplTest
{
  @Mock
  private UserRepository userRepository;

  @Mock
  private UserService userService;

  @Test
  void testCreate()
  {
    UserCreateRequest request = new UserCreateRequest();
    request.setUserName("new");
    request.setPassword(new char[]{'d'});
    request.setRepeatedPassword(new char[]{'d'});

    User user = mock(User.class);
    when(userRepository.findByUserName("new")).thenReturn(Optional.of(user));

    userService.create(request);
  }

  //  @TestConfiguration
  //  static class UserServiceImplTestContextConfiguration
  //  {
  //    @Bean
  //    public UserService employeeService()
  //    {
  //      UserServiceImpl userService = new UserServiceImpl();
  //      return userService;
  //    }
  //  }
}