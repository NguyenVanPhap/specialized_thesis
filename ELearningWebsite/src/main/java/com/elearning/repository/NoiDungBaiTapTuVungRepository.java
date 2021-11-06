package com.elearning.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.entities.*;


@Repository
public interface NoiDungBaiTapTuVungRepository extends JpaRepository<NoiDungBaiTapTuVung, Integer> {

    List<NoiDungBaiTapTuVung> findByBaitaptuvung(BaiTapTuVung baitaptuvung);

}