package co.gukjin.surveytest.survey.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.gukjin.surveytest.survey.Question.Question;
import co.gukjin.surveytest.survey.vo.AnsweredDateVO;

@Controller
@RequestMapping("/survey")	// 이런 주소로 들어 왔을 때
public class SurveyController {

	/*
	@GetMapping	// get 방식
	public String form() {
		return "survey/surveyForm";
	}
	
	@PostMapping	// post 방식
//	public String submit(@ModelAttribute("ansData") AnsweredDateVO vo) {
	public String submit(AnsweredDateVO data, Model model) {
		// 여기서 db 저장 작업을 처리한다.
		
		model.addAttribute("ansData", data);	//@ModelAttribute를 쓸 때는 안 해도 된다.
	
		return "survey/submitted";
	}
	 */
	
	@GetMapping
	public String form(Model model) {
		
		List<Question>questions = createQuestions();
		model.addAttribute("questions", questions);
		System.out.println("ㅁㄹ : " + questions.toString());
		return "survey/surveyForm";
	}

	private List<Question> createQuestions() {
		
		// Arrays.asList 가 뭔지 찾아봤는데 ArrayList<> 와 비교할 수 있는 대상이다.
		// ArrayList 는 add.list로 항목 추가가 가능한데 이건 불가능하다.
		// 사용처는 사이즈를 늘릴 필요 없거나 못바꾸게 할 곳의 List를 만들 때 사용한느 듯 한다.
		
		Question q1 = new Question("역할은 뭐?", Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("도구는 뭐?", Arrays.asList("이클", "인텔리J", "서브라임"));
		Question q3 = new Question("말해");
		
		return Arrays.asList(q1, q2, q3);
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredDateVO vo) {
		
		return "survey/submiiteed";
	}
}
