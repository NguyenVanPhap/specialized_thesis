
package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.entities.KhoaHoc;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer> {
	 List<KhoaHoc> findByKhoahocid(Integer khoaHocId); 

}
