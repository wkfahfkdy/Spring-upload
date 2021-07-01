package co.gukjin.surveytest.member.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.gukjin.surveytest.member.service.MemberService;
import co.gukjin.surveytest.member.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberDao;
	
	@RequestMapping("loginConfirm.do")
	public String loginConfirm(MemberVO vo, HttpSession session, Model model) {

		MemberVO vo2 = memberDao.loginCheck(vo);

		if(vo2.getEmail() != null) {
			
			session.setAttribute("email", vo2.getEmail());
			model.addAttribute("message", "님 환영");
			model.addAttribute("member", vo2);
			
		} else {
			
			model.addAttribute("message", "그런 놈 없다");
		}
		
		return "home";
	}
	
	@RequestMapping("memberList")
	public String memberList(Model model, @RequestParam(value = "state", defaultValue="Y") String state) {
		
		model.addAttribute("members", memberDao.memberSelectList(state));
		
		return "member/memberList";
	}
	
	@RequestMapping("member/memberInsert")
	public String memberInsert() {
		
		return "member/memberInsert";
	}
	
	@RequestMapping("memberRegister.do")
	public String memberRegester(MemberVO vo, Model model) throws IOException {
		
		// 여기서 파일 업로드 처리
		MultipartFile file = vo.getMultifile();
		String fileName = file.getOriginalFilename();
		
		// timestamp 사용하면 중복파일명 해결가능 혹은 중복되지 않는 랜덤 시드값이나 유니크 키 만들어서 OR UUID???
		UUID fileUuid = UUID.randomUUID();	// aliasFileName 대신 UUID 만들었음
		String aliasFileName = fileUuid.toString(); // UUID 등을 이용한 유니크 key값 넣기
		vo.setFileUuid(aliasFileName);
		
		File target = new File("D:\\", aliasFileName);	// aliasFileName 설정했으면 fileName 대신 넣기
		file.transferTo(target);
		
		/*  
		 * // 위의 transferTo 혹은 아래의 내용을 쓰면 전송이 된다
		 * try { FileCopyUtils.copy(file.getBytes(), target); } catch(Exception e) {
		 * e.printStackTrace(); }
		 */
		
		
		// fileName 에는 업로드 파일명으로 들어가고, 그와 구별하기 위한 fileUuid에는 UUID 코드가 들어간다. 실제 저장되는 파일명은 UUID
		vo.setFileName(fileName);	
		int n = memberDao.memberInsert(vo);
		
		String message = "";
		
		if(n != 0) {
			message = "입력ㅇ";
		} else {
			message = "입력x";
		}
		
		model.addAttribute("message", message);
		
		return "member/memberRegester";
	}
	
	@RequestMapping("fileDown.do")
	public String fileDown(@RequestParam("filePath") String filePath,
				@RequestParam("fileName") String fileName,
				HttpServletResponse res) throws Exception{
		
		// 여기서 파일 다운로드 처리
		// param을 저거 2개 가져오는 이유 : 
		// 			filePath가 실질적인 파일명이지만, 다시 다운로드 받을 때는 fileName의 이름으로 받아야하기 때문
		/*  // 내가 만든 거 - 다운로드라기보다는 카피 느낌임
		String fiPath = "D:\\" + filePath;
		String fiName = "C:\\temp\\" + fileName;
		
		File fPath = new File(fiPath);
		File fName = new File(fiName);
		
		try {
			
			FileInputStream fis = new FileInputStream(fPath);
			FileOutputStream fos = new FileOutputStream(fName);
			
			int fileByte = 0;
			
			while((fileByte = fis.read()) != -1) {
				fos.write(fileByte);
			}
			
			fis.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		//===========================================================================================
		
		/*
		// 국진 코드 - 밑에 다운로드 표시가 나오는거 << 문제점 : return "redirect:~~~" 에서 redirect 가 안됨. >> String 타입이 아니라 void 타입이라 return 없이 했음
		String fPath = "D:\\" + filePath;
		String docName;
		try {
			docName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			res.setContentType("text/plain");
			res.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		final File file_down = new File(fPath);
		InputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(file_down);
			IOUtils.copy(inputStream, res.getOutputStream());
			res.flushBuffer();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
		
		//===========================================================================================
		
		// 교수님 코드
		File file = null;
		InputStream is;
		OutputStream os;
		 
		try {
			file = new File("d:\\", filePath);
			is = new FileInputStream(file);
			os = res.getOutputStream();
			
			fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
			res.reset();
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			//res.setHeader(, fileName);

			byte[] b = new byte[2048];
			int leng = 0;
			while((leng = is.read(b)) != -1) {
				os.write(b,0,leng);
			}
			is.close();
			os.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:memberList";
	}
}
