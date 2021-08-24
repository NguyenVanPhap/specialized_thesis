package com.elearning.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.entities.NguoiDung;
import com.elearning.entities.VaiTro;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

	NguoiDung findByEmail(String email);
	
	Page<NguoiDung> findByVaiTro(VaiTro vaiTro, Pageable of);

	List<NguoiDung> findByVaiTro(VaiTro vaiTro);	
}
