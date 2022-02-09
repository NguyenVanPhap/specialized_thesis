package com.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elearning.entities.ListeningLecture;
import com.elearning.helper.ApiRes;
import com.elearning.repository.ListeningLectureRepository;

@Service
public class ListeningLectureService {
	@Autowired
	ListeningLectureRepository baiListeningLectureRepo;

	public ApiRes<Object> save(ListeningLecture listeningLecture) {

		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			apiRes.setObject(baiListeningLectureRepo.save(listeningLecture));
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	}

	public ApiRes<Object> getInfor(int id) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			List<ListeningLecture> lstListeningLectures = baiListeningLectureRepo.findById(id);
			apiRes.setObject(lstListeningLectures.get(0));
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	}

	public ApiRes<Object> getList(int page, int size) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			Page<ListeningLecture> pageListeningLecture = baiListeningLectureRepo.findAll(PageRequest.of(page, size));
			apiRes.setObject(pageListeningLecture);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	}

	public ApiRes<Object> getAll() {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			List<ListeningLecture> lsListeningLectures = baiListeningLectureRepo.findAll();
			apiRes.setObject(lsListeningLectures);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;

	}

	public ApiRes<Object> delete(int id) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			baiListeningLectureRepo.deleteById(id);
			apiRes.setObject(true);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	}

	public ApiRes<Object> search(String search) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			List<ListeningLecture> lsListeningLectures = baiListeningLectureRepo.search(search);
			apiRes.setObject(lsListeningLectures);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	}
}
