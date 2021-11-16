package com.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.entities.Vocabulary;
import com.elearning.entities.VocabularyContent;
import com.elearning.repository.DetailVocabularyRepository;
import com.elearning.service.DetailVocabularyService;

@Service
public class DetailVocabularyServiceImpl implements DetailVocabularyService {
	@Autowired
	DetailVocabularyRepository ndbaitaptuvungrepository;

	@Override
	public void save(VocabularyContent noidungbaitaptuvung) {
		ndbaitaptuvungrepository.save(noidungbaitaptuvung);
	}
	@Override
	public void delete(Integer id) {
        ndbaitaptuvungrepository.deleteById(id);
    }
    @Override
    public List<VocabularyContent> getListBaiTapTuVung(Vocabulary baitaptuvung) {
        return ndbaitaptuvungrepository.findByBaitaptuvung(baitaptuvung);
    }
}
