package pl.carshare.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author radziejoski
 **/
@Service("userService")
@Transactional
@EnableWebSecurity
public class UserServiceImpl implements UserService
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public User create(UserCreateRequest request) throws UserWithLoginAlreadyExistsException, PasswordMismatchException
  {
    User user = request.create(userRepository::findByUserName, getPasswordEncoder());
    return userRepository.save(user);
  }

  @Override
  public boolean login(UserLoginRequest request) throws InvalidUserNameOrPasswordException
  {
    return request.login(userRepository::findByUserNameAndPassword, getPasswordEncoder());
  }

  @Override
  public User getById(Long id) throws NoSuchUserException
  {
    return userRepository.findById(id)
      .orElseThrow(() -> NoSuchUserException.of(id));
  }

  @Bean
  PasswordEncoder getPasswordEncoder()
  {
    return new BCryptPasswordEncoder();
  }
}
