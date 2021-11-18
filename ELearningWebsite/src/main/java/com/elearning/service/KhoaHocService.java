
package com.elearning.service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.elearning.entities.KhoaHoc;

public interface KhoaHocService {

	void save(KhoaHoc khoahoc);

	List<KhoaHoc> getKhoaHoc(int id);

	Page<KhoaHoc> getKhoaHoc(int page, int limit);

	List<KhoaHoc> getAllKhoaHoc();

	void delete(int id);

	/* List<KhoaHoc> searchKhoaHoc(String search); */

}
