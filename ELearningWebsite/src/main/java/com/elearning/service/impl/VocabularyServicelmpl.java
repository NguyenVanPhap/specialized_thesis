package com.elearning.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elearning.entities.Grammar;
import com.elearning.entities.Vocabulary;
import com.elearning.repository.VocabularyRepository;
import com.elearning.repository.GrammarRepository;
import com.elearning.service.VocabularyService;

@Service
public class VocabularyServicelmpl implements VocabularyService{
	@Autowired
	VocabularyRepository baitaptuvungRepository;

    @Override
    public List<Vocabulary> findAll() {
        return (List<Vocabulary>) baitaptuvungRepository.findAll();
    }

    @Override
    public void save(Vocabulary baitaptuvung) {
        baitaptuvungRepository.save(baitaptuvung);
    }

    @Override
    public void delete(Integer id) {
        baitaptuvungRepository.deleteById(id);
    }

    @Override
    public Page<Vocabulary> getBaiTapTuVung(int page, int size) {
        return baitaptuvungRepository.findAll(PageRequest.of(page, size));
        //return baitaptuvungRepository.f

    }

    @Override
    public Optional<Vocabulary> getBaiTuVungById(Integer id) {
        return baitaptuvungRepository.findById(id);
    }

    @Override
    public List<Vocabulary> getBaiTapTuVung(int id) {
        return baitaptuvungRepository.findByVocabularyid(id);
    }

    @Override
    public List<Vocabulary> searchListBaiTapTuVung(String search) {
        return baitaptuvungRepository.searchVocab(search);
    }
}
