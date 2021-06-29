package co.gukjin.surveytest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("home.do")
	public String home() {
		
		return "home";
	}
	
	@RequestMapping("login.do")
	public String login(Model model) {
		
		return "member/login";
	}
}
