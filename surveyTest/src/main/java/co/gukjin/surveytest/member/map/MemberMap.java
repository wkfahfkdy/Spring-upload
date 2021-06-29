package co.gukjin.surveytest.member.map;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import co.gukjin.surveytest.member.vo.MemberVO;

public interface MemberMap {

	@Select("select * from member where email = #{email} and password = #{password}")
	MemberVO loginCheck(MemberVO vo);

	List<MemberVO> memberSelectList(String state);
	
	int memberInsert(MemberVO vo);
	MemberVO memberSelect(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
}
