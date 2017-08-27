package com.daview.controller;



import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;


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
		BoardDto board = service.getBoardView(b_num);
		model.addAttribute("board",board);
	}
	
}
