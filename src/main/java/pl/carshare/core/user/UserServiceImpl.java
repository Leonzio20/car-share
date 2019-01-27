package pl.carshare.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User create(UserCreateRequest request) throws UserWithLoginAlreadyExistsException, PasswordMismatchException
  {
    User user = request.create(userRepository::findByUserName, passwordEncoder);
    return userRepository.save(user);
  }

  @Override
  public void login(UserLoginRequest request) throws InvalidUserNameOrPasswordException
  {
    request.login(userRepository::findByUserName, passwordEncoder);
  }

  @Override
  public User getById(Long id) throws NoSuchUserException
  {
    return userRepository.findById(id)
      .orElseThrow(() -> NoSuchUserException.of(id));
  }
}
