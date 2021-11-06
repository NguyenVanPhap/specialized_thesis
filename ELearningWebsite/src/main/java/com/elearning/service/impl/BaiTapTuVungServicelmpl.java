package com.elearning.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elearning.entities.BaiGrammar;
import com.elearning.entities.BaiTapTuVung;
import com.elearning.repository.BaiTapTuVungRepository;
import com.elearning.repository.GrammarRepository;
import com.elearning.service.BaiTapTuVungService;

@Service
public class BaiTapTuVungServicelmpl implements BaiTapTuVungService{
	@Autowired
	BaiTapTuVungRepository baitaptuvungRepository;

    @Override
    public List<BaiTapTuVung> findAll() {
        return (List<BaiTapTuVung>) baitaptuvungRepository.findAll();
    }

    @Override
    public void save(BaiTapTuVung baitaptuvung) {
        baitaptuvungRepository.save(baitaptuvung);
    }

    @Override
    public void delete(Integer id) {
        baitaptuvungRepository.deleteById(id);
    }

    @Override
    public Page<BaiTapTuVung> getBaiTapTuVung(int page, int size) {
        return baitaptuvungRepository.findAll(PageRequest.of(page, size));
        //return baitaptuvungRepository.f

    }

    @Override
    public Optional<BaiTapTuVung> getBaiTuVungById(Integer id) {
        return baitaptuvungRepository.findById(id);
    }

    @Override
    public List<BaiTapTuVung> getBaiTapTuVung(int id) {
        return baitaptuvungRepository.findByBaitaptuvungid(id);
    }

    @Override
    public List<BaiTapTuVung> searchListBaiTapTuVung(String search) {
        return baitaptuvungRepository.searchVocab(search);
    }
}
