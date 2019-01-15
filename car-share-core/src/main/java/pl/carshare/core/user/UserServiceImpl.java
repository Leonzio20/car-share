package pl.carshare.core.user;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author radziejoski
 **/
@Service("userService")
@Transactional
class UserServiceImpl implements UserService
{
  @Autowired
  private UserRepository userRepository;

  @Override
  public User create(UserCreateRequest request)
  {
    User user = request.create();
    return userRepository.save(user);
  }
}
