package com.elearning.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.elearning.entities.Vocabulary;
import com.elearning.entities.Course;
import com.elearning.entities.NguoiDung;
import com.elearning.entities.Role;
import com.elearning.service.*;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController {

	@Autowired
	private CourseService courseService;

	@Autowired
	NguoiDungService nguoiDungService;

	@Autowired
	VocabularyService baitaptuvungService;

	public NguoiDung getSessionUser(HttpServletRequest request) {
		return (NguoiDung) request.getSession().getAttribute("loggedInUser");

	}

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
		return "admin/grammarmanagement";
	}

	@GetMapping("/courses")
	public String cources() {
		return "admin/QuanLyKhoahoc";
	}

	@RequestMapping(value = "/coursedetail")
	public String CourseDetail(@RequestParam("courseId") int id, Model model) {
		Course course = courseService.getCourse(id).get(0);
		model.addAttribute("currentCourse", course);
		return "admin/lessonmanagement";
	}

	@GetMapping("/vocab")
	public String quanLyVocab(Model model) {
		model.addAttribute("listVocab", baitaptuvungService.findAll());
		model.addAttribute("vocabbulary", new Vocabulary());
		return "admin/quanLyVocab";

	}

	@GetMapping("/account")
	public String quanLyTaiKhoan(Model model) {
		model.addAttribute("listRole", Role.values());
		return "admin/accountmanagement";
	}

	@GetMapping("/test")
	public String Test() {
		return "admin/grammarmanagement";
	}

}
