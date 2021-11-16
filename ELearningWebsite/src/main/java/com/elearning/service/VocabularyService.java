package com.elearning.service;

import java.util.*;

import org.springframework.data.domain.Page;

import com.elearning.entities.Grammar;
import com.elearning.entities.Vocabulary;

public interface VocabularyService {
	List<Vocabulary> findAll();

    void save(Vocabulary baitaptuvung);

    void delete(Integer id);

    Page<Vocabulary> getBaiTapTuVung(int page, int limit);

    Optional<Vocabulary> getBaiTuVungById(Integer id);

    List<Vocabulary> getBaiTapTuVung(int id);

    List<Vocabulary> searchListBaiTapTuVung(String search);
    
    //List<BaiTapTuVung> getAllBaiTapTuVung();


}
