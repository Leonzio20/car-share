package carshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.carshare.core.user.UserService;

@SpringBootApplication
@ComponentScan("pl.carshare.core")
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
    userService.testConnection();
  }
}
