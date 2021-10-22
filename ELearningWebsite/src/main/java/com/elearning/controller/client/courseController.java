package com.elearning.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class courseController {

	@GetMapping("/course")
	public String course() {
		return "client/list-course";

	}
	@GetMapping("/video")
	public String video() {
		return "client/videopage";

	}
}
