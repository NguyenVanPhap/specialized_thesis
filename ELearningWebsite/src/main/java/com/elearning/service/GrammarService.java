package com.elearning.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.elearning.entities.Grammar;

public interface GrammarService {
	void save(Grammar baigrammar);
	List<Grammar> getGrammar(int id);
	
	Page<Grammar> getGrammar(int page, int limit);
	
	List<Grammar> getAllGrammar();

	void delete(int id);
	
	List<Grammar> searchListBaiGrammar(String search);

}
