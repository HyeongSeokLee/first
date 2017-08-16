package com.daview.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daview.dao.MemberDaoImpl;
import com.daview.dto.MemberDto;
import com.daview.service.EmailChk;



@Controller
@RequestMapping("/member/*")
public class MemberController extends EmailChk{
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//구현해 놓은 MemberDao 설정 해둠
	@Inject
	private MemberDaoImpl m_service;
	
	@RequestMapping(value = "insertMember",  method = {RequestMethod.GET, RequestMethod.POST})
	public String insertMember(String email,String address,String email_auth,Model model) {
		if(email==null){email="";}
		if(address==null){address="";}
		if(email_auth==null){email_auth="N";}

		model.addAttribute("email",email);
		model.addAttribute("address",address);
		model.addAttribute("email_auth",email_auth);

		
		return "member/insertMember";
	}
	
	//이메일 인증시 사용
	@RequestMapping(value="emailCheck",method=RequestMethod.GET)
	public String emailChk(@RequestParam String email,@RequestParam String address,Model model){
		String numberCode = ""; // 인증코드
		String email_address=email+'@'+address;
		numberCode = ranNum();
		
		sendMail(email_address.toString(), numberCode);
		
		model.addAttribute("numberCode", numberCode);
		model.addAttribute("email", email);
		model.addAttribute("address",address);
		return "member/emailCheck";
	}
	
	//닉네임 중복체크시 사용
	@RequestMapping(value="nickCheck",method=RequestMethod.GET)
	public String nickChk(@RequestParam String m_nick,Model model){
	    int result=m_service.nickChk(m_nick);
	    System.out.println(result);
	    model.addAttribute("result",result);
	    model.addAttribute("m_nick",m_nick);
		return "member/nickCheck";
	}
	
	//회원가입
	@RequestMapping(value="insertPro",method = RequestMethod.POST)
	public String insertMem(@RequestParam String m_email,
			@RequestParam String address,
			@RequestParam String m_pw,
			@RequestParam String m_nick,
			Model model){
		MemberDto md = new MemberDto();
		md.setM_email(m_email+"@"+address);
		md.setM_nick(m_nick);
		md.setM_pw(m_pw);
		int result=m_service.insertMember(md);
		/*System.out.println("insert result::"+result);*/
		model.addAttribute("result",result);
		return "member/insertPro";
	}
	
	//로그인창 이동
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "member/loginForm";
	}
	
	//로그인
	@RequestMapping(value = "loginPro", method = RequestMethod.POST)
	public String loginPro(@RequestParam String m_email
			,@RequestParam String m_pw
			,HttpSession session
			, Model model) {
		MemberDto md = new MemberDto();
		md.setM_email(m_email);
		md.setM_pw(m_pw);
		System.out.println("loginPro email::"+m_email);
		System.out.println("loginPro m_pw::"+m_pw);
		
		int result=m_service.userChk(md);
		String m_nick = m_service.getM_Nick(m_email);
		System.out.println("loginPro m_nick:::"+m_nick);
		if(result>0) session.setAttribute("m_email", m_email);
		System.out.println("logionPro session확인::"+session.getAttribute("m_email"));
		
		model.addAttribute("m_email",m_email);
		model.addAttribute("result",result);
		model.addAttribute("m_nick",m_nick);

		return "member/loginPro";
	}
	//로그아웃
	@RequestMapping(value="logoutPro")
	public String logoutPro(HttpSession session, Model model) {
		session.invalidate(); //세션에 있는 것 삭제
		return "member/logoutPro";
	}
	
	//회원 수정
	@RequestMapping(value="memberModifyForm")
	public String memberModifyForm(HttpSession session, 
			String m_email,
			Model model) {
		m_email=(String)session.getAttribute("m_email");
		model.addAttribute("m_email",m_email);
		return "member/memberModifyForm";
	}


}
