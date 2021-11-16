package com.elearning.service;

import java.util.List;

import com.elearning.entities.Vocabulary;
import com.elearning.entities.VocabularyContent;

public interface DetailVocabularyService {
	void save(VocabularyContent noidungbaitaptuvung);

    void delete(Integer id);

    List<VocabularyContent> getListBaiTapTuVung(Vocabulary baitaptuvung);
}
