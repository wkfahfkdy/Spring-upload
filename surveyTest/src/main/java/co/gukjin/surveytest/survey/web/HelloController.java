package co.gukjin.surveytest.survey.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class HelloController {

	@RequestMapping("/hello")
	public String hello(Model model,
						@RequestParam(value = "name", required = false) String name)
	{
		
		model.addAttribute("greeting", "안녕," + name);
		return "hello";
	}
	
}
