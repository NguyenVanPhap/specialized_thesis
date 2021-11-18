
package com.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elearning.entities.KhoaHoc;
import com.elearning.repository.KhoaHocRepository;
import com.elearning.service.KhoaHocService;

@Service
public class KhoaHocServiceImpl implements KhoaHocService {

	@Autowired
	KhoaHocRepository khoaHocRepo;

	@Override
	public void save(KhoaHoc baigrammar) {
		khoaHocRepo.save(baigrammar);
	}

	@Override
	public List<KhoaHoc> getKhoaHoc(int id) {
		return khoaHocRepo.findByKhoahocid(id);
	}

	@Override
	public Page<KhoaHoc> getKhoaHoc(int page, int size) {
		return khoaHocRepo.findAll(PageRequest.of(page, size));

	}

	@Override
	public List<KhoaHoc> getAllKhoaHoc() {
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
