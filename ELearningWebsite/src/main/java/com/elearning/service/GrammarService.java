package com.elearning.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.elearning.entities.Grammar;

public interface GrammarService {
	void save(Grammar baigrammar);
	List<Grammar> getBaiGrammar(int id);
	
	Page<Grammar> getBaiGrammar(int page, int limit);
	
	List<Grammar> getAllBaiGrammar();
	void delete(int id);
	
	List<Grammar> searchListBaiGrammar(String search);

}
