
package com.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elearning.entities.Course;
import com.elearning.repository.CourseRepository;
import com.elearning.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository khoaHocRepo;

	@Override
	public void save(Course baigrammar) {
		khoaHocRepo.save(baigrammar);
	}

	@Override
	public List<Course> getCourse(int id) {
		return khoaHocRepo.findByCourseid(id);
	}

	@Override
	public Page<Course> getCourse(int page, int size) {
		return khoaHocRepo.findAll(PageRequest.of(page, size));

	}

	@Override
	public List<Course> getAllCourse() {
		return khoaHocRepo.findAll();
	}

	@Override
	public void delete(int id) {
		khoaHocRepo.deleteById(id);
	}

	/*
	 * @Override public List<KhoaHoc> searchListKhoaHoc(String search) { return
	 * khoaHocRepo.searchGrammar(search);
	 * 
	 * }
	 */

}
