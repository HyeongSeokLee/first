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
@RequestMapping("/admin/*")
public class AdminController {
	private static final Logger logger = 
			LoggerFactory.getLogger(AdminController.class);
	@Inject
	private BoardService service;
	
@RequestMapping(value = "adminMain", method = {RequestMethod.GET, RequestMethod.POST})
public void adminMain(Criteria cri, Model model) throws Exception{
	logger.info("/adminMain 호출");

	model.addAttribute("list",service.listSearch(cri));
	PageMaker pageMaker = new PageMaker();
	pageMaker.setCri(cri);
	pageMaker.setTotalCount(service.listSearchCount(cri));
	model.addAttribute("cri",cri);
	model.addAttribute("pageMaker",pageMaker);
}
}
