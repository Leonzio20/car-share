package pl.carshare.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.carshare.core.user.UserCreateRequest;
import pl.carshare.core.user.UserService;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("pl.carshare.core")
@EnableJpaRepositories("pl.carshare.core")
@EntityScan("pl.carshare.core")
public class Application implements CommandLineRunner
{
  @Autowired
  private UserService userService;

  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
  }
  @Override
  public void run(String... args) throws Exception {
    UserCreateRequest request = new UserCreateRequest();
    request.setUserName("ja1n");
    userService.create(request);
  }
}
