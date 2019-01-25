package pl.carshare.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.carshare.core.user.UserLoginRequest;
import pl.carshare.core.user.UserService;

/**
 * @author radziejoski
 **/
@Controller
public class IndexController
{

  @Autowired
  private UserService userService;

  @RequestMapping({"", "/", "/index"})
  public String getIndexPage()
  {
    UserLoginRequest loginRequest = new UserLoginRequest();
    loginRequest.setUserName("");
    loginRequest.setPassword("");
    return "index";
  }
}
