package com.elearning.service;

import java.util.*;

import org.springframework.data.domain.Page;

import com.elearning.entities.BaiGrammar;
import com.elearning.entities.BaiTapTuVung;

public interface BaiTapTuVungService {
	List<BaiTapTuVung> findAll();

    void save(BaiTapTuVung baitaptuvung);

    void delete(Integer id);

    Page<BaiTapTuVung> getBaiTapTuVung(int page, int limit);

    Optional<BaiTapTuVung> getBaiTuVungById(Integer id);

    List<BaiTapTuVung> getBaiTapTuVung(int id);

    List<BaiTapTuVung> searchListBaiTapTuVung(String search);
    
    //List<BaiTapTuVung> getAllBaiTapTuVung();


}
