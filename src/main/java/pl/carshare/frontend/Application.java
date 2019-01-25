package pl.carshare.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.carshare.core.ApplicationConfig;
import pl.carshare.core.user.UserCreateRequest;
import pl.carshare.core.user.UserService;

@SpringBootApplication(scanBasePackageClasses = ApplicationConfig.class)
public class Application implements CommandLineRunner
{
  @Autowired
  private UserService userService;

  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception
  {
    UserCreateRequest request = new UserCreateRequest();
    request.setUserName("ja1n");
    request.setPassword("ja1n");
    request.setRepeatedPassword("ja1n");
    //        userService.create(request);
  }
}
