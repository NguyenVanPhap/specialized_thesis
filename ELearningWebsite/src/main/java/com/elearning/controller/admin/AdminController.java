package com.elearning.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.elearning.entities.Vocabulary;
import com.elearning.entities.NguoiDung;
import com.elearning.entities.Role;
import com.elearning.service.*;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController {

	
	@Autowired
	NguoiDungService nguoiDungService;
	
	@Autowired
	VocabularyService baitaptuvungService;
	
	@ModelAttribute("loggedInUser")
	public NguoiDung loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return nguoiDungService.findByEmail(auth.getName());
	}
	
	
	@GetMapping()
	public String loginPage(Model model) {
		return "admin/homepage";
	}
	
	@GetMapping("/grammar")
	public String quanLyGrammar() {
		return "admin/quanLyGrammar";
	}

	@GetMapping("/cources")
	public String cources() {
		return "admin/QuanLyKhoahoc";
	}
	@GetMapping("/vocab")
	public String quanLyVocab(Model model) {
		model.addAttribute("listVocab", baitaptuvungService.findAll());
		model.addAttribute("vocabbulary", new Vocabulary());
		return "admin/quanLyVocab";

	}

	public NguoiDung getSessionUser(HttpServletRequest request) {
		return (NguoiDung) request.getSession().getAttribute("loggedInUser");
		
	}
}
