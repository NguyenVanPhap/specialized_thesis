package com.elearning.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.entities.*;


@Repository
public interface DetailVocabularyRepository extends JpaRepository<VocabularyContent, Integer> {

    List<VocabularyContent> findByBaitaptuvung(Vocabulary baitaptuvung);

}