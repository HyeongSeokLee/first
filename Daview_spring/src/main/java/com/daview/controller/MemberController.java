package com.daview.controller;



import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import com.daview.dto.MemberDto;
import com.daview.service.EmailChk;
import com.daview.service.MemberService;




@Controller
@RequestMapping("/member/*")
public class MemberController extends EmailChk{
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService m_service;
	
	
	//이메일 인증시 사용
	@RequestMapping(value="/emailCheck",method=RequestMethod.GET)
	public String emailChk(@RequestParam String email,@RequestParam String address,Model model) throws Exception{
		logger.info("/member/emailCheck 호출");
		String numberCode = ""; // 인증코드
		String email_address=email+'@'+address;
		numberCode = ranNum();
		
		sendMail(email_address.toString(), numberCode);
		
		model.addAttribute("numberCode", numberCode);
		model.addAttribute("email", email);
		model.addAttribute("address",address);
		return "member/emailCheck";
	}
	
	@RequestMapping(value = "/insertMember",  method = {RequestMethod.GET, RequestMethod.POST})
	public String insertMember(String email,String address,String email_auth,Model model) throws Exception {
		logger.info("/member/insertmember 호출");
		if(email==null){email="";}
		if(address==null){address="";}
		if(email_auth==null){email_auth="N";}

		model.addAttribute("email",email);
		model.addAttribute("address",address);
		model.addAttribute("email_auth",email_auth);
	
		return "member/insertMember";
	}	

	//회원가입
	@RequestMapping(value="/insertPro",method = RequestMethod.POST)
	public String insertMem(@RequestParam String m_email,
			@RequestParam String address,
			@RequestParam String m_pw,
			@RequestParam String m_nick,
			Model model)throws Exception{
		logger.info("/member/insertPro 호출");
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
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() throws Exception{
		logger.info("/member/loginForm 호출");
		return "member/loginForm";
	}
	
	//로그인
	@RequestMapping(value = "/loginPro", method = RequestMethod.POST)
	public String loginPro(@RequestParam String m_email
			,@RequestParam String m_pw
			,HttpSession session
			, Model model) throws Exception{
		logger.info("/member/loginPro 호출");
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
	@RequestMapping(value="/logoutPro")
	public String logoutPro(HttpSession session, Model model) throws Exception{
		logger.info("/member/logoutPro 호출");
		session.invalidate(); //세션에 있는 것 삭제
		return "member/logoutPro";
	}
	
	//회원 수정
	@RequestMapping(value="/memberModifyForm")
	public String memberModifyForm(HttpSession session, 
			String m_email,
			Model model)throws Exception {
		logger.info("/member/memberModifyForm 호출");
		m_email=(String)session.getAttribute("m_email");
		model.addAttribute("m_email",m_email);
		return "member/memberModifyForm";
	}

	
/* //회원 수정 Pro  수정 전
		@RequestMapping(value="/memberModifyPro")
		public String memberModifyPro(HttpSession session, 
				String m_email,
				Model model)throws Exception {
			logger.info("/member/memberModifyForm 호출");
			m_email=(String)session.getAttribute("m_email");
			model.addAttribute("m_email",m_email);
			return "member/memberModifyForm";
		}*/
	
	
	//닉네임 중복체크시 사용
	@RequestMapping(value="/nickCheck",method=RequestMethod.GET)
	public String nickChk(@RequestParam String m_nick,Model model)throws Exception{
		logger.info("/member/nickCheck 호출");
	    int result=m_service.nickChk(m_nick);
	    System.out.println(result);
	    model.addAttribute("result",result);
	    model.addAttribute("m_nick",m_nick);
		return "member/nickCheck";
	}
	

}
