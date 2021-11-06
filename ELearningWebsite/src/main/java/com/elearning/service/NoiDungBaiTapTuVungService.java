package com.elearning.service;

import java.util.List;

import com.elearning.entities.BaiTapTuVung;
import com.elearning.entities.NoiDungBaiTapTuVung;

public interface NoiDungBaiTapTuVungService {
	void save(NoiDungBaiTapTuVung noidungbaitaptuvung);

    void delete(Integer id);

    List<NoiDungBaiTapTuVung> getListBaiTapTuVung(BaiTapTuVung baitaptuvung);
}
