package com.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elearning.entities.BaiGrammar;
import com.elearning.repository.GrammarRepository;
import com.elearning.service.GrammarService;

@Service
public class GrammarServiceImpl implements GrammarService {
	@Autowired
	GrammarRepository baigrammarRepo;
	
	@Override
	public void save(BaiGrammar baigrammar) {
		baigrammarRepo.save(baigrammar);
	}
	
	@Override
	public List<BaiGrammar> getBaiGrammar(int id){
		return baigrammarRepo.findByBaigrammarid(id);
	}
	
	@Override
	public Page<BaiGrammar> getBaiGrammar(int page, int size){
		return baigrammarRepo.findAll(PageRequest.of(page, size));
		
	}
	
	@Override
	public List<BaiGrammar>getAllBaiGrammar(){
		return baigrammarRepo.findAll();
	}
	
	@Override
	public void delete(int id) {
		baigrammarRepo.deleteById(id);
	}
	
	@Override
	public List<BaiGrammar> searchListBaiGrammar(String search){
		return baigrammarRepo.searchGrammar(search);
		
	}
}
