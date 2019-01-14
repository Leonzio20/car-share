package pl.carshare.core.user;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
  private SessionFactory sessionFactory;

  @Override
  public void testConnection()
  {
    Session session = sessionFactory.getCurrentSession();

    User user = new User();
    user.setLogin("test2");
    user.setPassword("test2");
    session.persist(user);
  }
}
