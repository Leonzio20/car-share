package pl.carshare.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.carshare.core.passage.PassageCreateRequest;
import pl.carshare.core.passage.PassageService;

@Controller
public class PassageController
{
  @Autowired
  private PassageService passageService;

  @RequestMapping(value = "/passage", method = RequestMethod.PUT)
  public String submit(@ModelAttribute("passage") PassageCreateRequest passage)
  {
    passageService.create(passage);
    return "dashboard";
  }
}
