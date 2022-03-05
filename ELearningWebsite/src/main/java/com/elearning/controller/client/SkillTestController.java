package com.elearning.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elearning.entities.ReadingExercise;
import com.elearning.service.ReadingExerciseService;

@Controller
@RequestMapping("/test/skill-test")
public class SkillTestController {

	@Autowired
	private ReadingExerciseService ReadingExerciseService;

	@GetMapping("/part-{partNumber}/{id}")
	public String baiDocPart(@PathVariable long id, @PathVariable int partNumber, Model model) {
		ReadingExercise objReadingExercise = ReadingExerciseService.findReadingExerciseById(id).get();
		model.addAttribute("ReadingExercise", objReadingExercise);
		return "client/ReadingExercise/ReadingExercisePart" + partNumber;
	}

	@GetMapping("/reading")
	public String Reading(Model model) {
		return "client/SkillTest/reading";
	}

	@GetMapping("")
	public String SkillTest(Model model) {
		return "client/SkillTest/skill-test";
	}

}
