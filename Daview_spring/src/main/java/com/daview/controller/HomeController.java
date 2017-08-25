package com.daview.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daview.dto.Criteria;
import com.daview.dto.PageMaker;
import com.daview.service.BoardService;

@Controller
public class HomeController {
	@Inject
	private BoardService service;
	private static final Logger logger = 
			LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() throws Exception{
		logger.info("/호출");
		return "redirect:/main";
	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main(Criteria cri, Model model) throws Exception{
		logger.info("/main 호출");

		model.addAttribute("list",service.listSearch(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("cri",cri);
		model.addAttribute("pageMaker",pageMaker);
	
	}
	
}
