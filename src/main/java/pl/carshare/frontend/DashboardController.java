package pl.carshare.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.carshare.core.passage.PassageService;

@Controller
public class DashboardController
{
  @Autowired
  private PassageService passageService;

  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
  public ModelAndView dashboard()
  {
    ModelAndView model = new ModelAndView();
    model.addObject("passages", passageService.search());
    model.setViewName("dashboard");
    return model;
  }
}
