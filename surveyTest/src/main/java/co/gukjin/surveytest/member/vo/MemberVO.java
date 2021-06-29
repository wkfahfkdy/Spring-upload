package co.gukjin.surveytest.member.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberVO {

	private String email;
	private String name;
	private String password;
	private String state;
	private String fileName;
	private String fileUuid;
	private MultipartFile multifile; // form에서 넘어오는 파일 객체
	
}
