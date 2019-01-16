package pl.carshare.core.user;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author leonzio
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest
{

}