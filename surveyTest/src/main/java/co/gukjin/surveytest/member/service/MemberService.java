package co.gukjin.surveytest.member.service;

import java.util.List;

import co.gukjin.surveytest.member.vo.MemberVO;

public interface MemberService {

	MemberVO loginCheck(MemberVO vo);
	int memberInsert(MemberVO vo);
	List<MemberVO> memberSelectList(String state);
	MemberVO memberSelect(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	
}
