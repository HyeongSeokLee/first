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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daview.dto.BoardDto;
import com.daview.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	
	@RequestMapping(value="/view")
	public void view(@RequestParam int b_num,Model model)throws Exception{
		logger.info("view 실행");
		BoardDto board = service.getBoardView(b_num);
		model.addAttribute("board",board);
	}
	
	@RequestMapping(value="/delPro_get",method = RequestMethod.GET)
	public String delPro(@RequestParam int b_num,@RequestParam String m_nick,HttpSession session,RedirectAttributes rttr) throws Exception{
		int result = 0;
		if(m_nick.equals(session.getAttribute("m_nick"))){
			 result = service.deleteBoard(b_num);
		}
		logger.info("delPro-GET 실행");
		rttr.addFlashAttribute("result",result);
		rttr.addFlashAttribute("b_num",b_num);
		return "redirect:/board/delPro_post";
	}
	@RequestMapping(value="/delPro_post")
	public String delPro() throws Exception{
		logger.info("delPro-POST 실행");
		return "/board/delPro";
	}
	@RequestMapping(value="/updateForm")
	public void updateForm(@RequestParam int b_num,Model model) throws Exception{
		logger.info("updateForm 실행");
		model.addAttribute("board",service.getBoardView(b_num));
	}
	@RequestMapping(value="/updatePro",method=RequestMethod.POST)
	public void updatePro(BoardDto dto,Model model) throws Exception{
		model.addAttribute("result",service.updateBoard(dto));
		logger.info("updatePro 실행");
	}
	@RequestMapping(value="/writeForm")
	public void writeForm() throws Exception{
		logger.info("writeForm 실행");
	}
	@RequestMapping(value="/writePro",method = RequestMethod.POST)
	public void writePro(BoardDto dto,Model model,HttpSession session) throws Exception{
		logger.info("writePro 실행");
		dto.setM_nick((String)session.getAttribute("m_nick"));
		int result = service.createBoard(dto);
		model.addAttribute("result",result);
	}
}
