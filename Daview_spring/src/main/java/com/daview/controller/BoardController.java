package com.daview.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daview.dao.MemberDaoImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private MemberDaoImpl m_service;
	
	@RequestMapping(value = "main", method = {RequestMethod.GET,RequestMethod.POST})
	public String main(HttpSession session,String m_email,String m_nick, Model model) {
		System.out.println("main session 확인::"+session.getAttribute("m_email"));
		
		m_email=(String)session.getAttribute("m_email");
		if((String)session.getAttribute("m_email")==null){m_email="";}
		else m_nick=m_service.getM_Nick(m_email);
			
		
		model.addAttribute("m_email", m_email);
		model.addAttribute("m_nick",m_nick);
		return "main";
	}
	
}
