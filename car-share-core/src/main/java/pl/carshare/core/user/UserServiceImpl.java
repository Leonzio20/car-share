package pl.carshare.core.user;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author radziejoski
 **/
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public User create(UserCreateRequest request)
  {
    User user = request.create(userRepository::findByUserName);
    return userRepository.save(user);
  }

  @Override
  public boolean login(UserLoginRequest request)
  {
    return request.login(userRepository::findByUserNameAndPassword);
  }
}
