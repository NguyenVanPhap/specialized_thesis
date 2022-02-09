package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elearning.entities.ListeningLecture;

@Repository
public interface ListeningLectureRepository extends JpaRepository<ListeningLecture, Integer> {
	List<ListeningLecture> findById(int id);

	@Query("select listeninglecture FROM ListeningLecture  listeninglecture WHERE  listeninglecture.Name LIKE CONCAT('%',:search,'%')")
	List<ListeningLecture> search(@Param("search") String search);
}
