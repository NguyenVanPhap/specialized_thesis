package com.elearning.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elearning.entities.Grammar;
import com.elearning.entities.KhoaHoc;
import com.elearning.service.KhoaHocService;

@Controller

@RequestMapping("/course")
public class courseController {

	@Autowired
	private KhoaHocService khoahocService;

	@GetMapping("")
	public String course(Model model) {

		List<KhoaHoc> listkKhoaHocs = khoahocService.getAllKhoaHoc();
		model.addAttribute("listData", listkKhoaHocs);
		return "client/list-course";

	}

	@GetMapping("/detail")
	public String DetalVocab(@RequestParam int courseId, Model model) {

		KhoaHoc khoahoc = khoahocService.getKhoaHoc(courseId).get(0);
		model.addAttribute("course", khoahoc);
		model.addAttribute("CourseId", khoahoc.getKhoaHocId());
		return "client/coursedetail";

	}

	@GetMapping("/lesson")
	public String lesson() {
		return "client/lesson";

	}
}
