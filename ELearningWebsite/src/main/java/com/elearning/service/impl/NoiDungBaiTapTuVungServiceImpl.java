package com.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.entities.BaiTapTuVung;
import com.elearning.entities.NoiDungBaiTapTuVung;
import com.elearning.repository.NoiDungBaiTapTuVungRepository;
import com.elearning.service.NoiDungBaiTapTuVungService;

@Service
public class NoiDungBaiTapTuVungServiceImpl implements NoiDungBaiTapTuVungService {
	@Autowired
	NoiDungBaiTapTuVungRepository ndbaitaptuvungrepository;

	@Override
	public void save(NoiDungBaiTapTuVung noidungbaitaptuvung) {
		ndbaitaptuvungrepository.save(noidungbaitaptuvung);
	}
	@Override
	public void delete(Integer id) {
        ndbaitaptuvungrepository.deleteById(id);
    }
    @Override
    public List<NoiDungBaiTapTuVung> getListBaiTapTuVung(BaiTapTuVung baitaptuvung) {
        return ndbaitaptuvungrepository.findByBaitaptuvung(baitaptuvung);
    }
}
