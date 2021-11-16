package com.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elearning.entities.Grammar;
import com.elearning.repository.GrammarRepository;
import com.elearning.service.GrammarService;

@Service
public class GrammarServiceImpl implements GrammarService {
	@Autowired
	GrammarRepository baigrammarRepo;
	
	@Override
	public void save(Grammar baigrammar) {
		baigrammarRepo.save(baigrammar);
	}
	
	@Override

	public List<Grammar> getGrammar(int id){

		return baigrammarRepo.findByGrammarid(id);
	}
	
	@Override
	public Page<Grammar> getGrammar(int page, int size){

		return baigrammarRepo.findAll(PageRequest.of(page, size));
		
	}
	
	@Override
	public List<Grammar>getAllGrammar(){
		return baigrammarRepo.findAll();
	}
	
	@Override
	public void delete(int id) {
		baigrammarRepo.deleteById(id);
	}
	
	@Override
	public List<Grammar> searchListBaiGrammar(String search){
		return baigrammarRepo.searchGrammar(search);
		
	}
}
