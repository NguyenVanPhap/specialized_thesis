package com.elearning.controller.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elearning.entities.BaiGrammar;
import com.elearning.entities.BaiTapTuVung;
import com.elearning.entities.NoiDungBaiTapTuVung;
import com.elearning.entities.CommentTuVung;
import com.elearning.entities.NguoiDung;
import com.elearning.service.BaiTapTuVungService;
import com.elearning.service.NguoiDungService;
import com.elearning.service.NoiDungBaiTapTuVungService;

@Controller
public class vocabController {
	@Autowired
	BaiTapTuVungService baitaptuvungService;
	
	@Autowired
    NoiDungBaiTapTuVungService noidungbaitaptuvungService;

	@Autowired
	private NguoiDungService nguoiDungService;

	@ModelAttribute("loggedInUser")
	public NguoiDung getSessionUser(HttpServletRequest request) {
		return (NguoiDung) request.getSession().getAttribute("loggedInUser");
	}

	@GetMapping("/listVocab")
    public String getPage(@RequestParam(defaultValue = "1") int page, Model model) {

        // default value lấy từ kết quả đầu tiên

        Page<BaiTapTuVung> list = baitaptuvungService.getBaiTapTuVung(page - 1, 4);
        int totalPage = list.getTotalPages();

        List<Integer> pagelist = new ArrayList<Integer>();

        // Lap ra danh sach cac trang
        if (page == 1 || page == 2) {
            for (int i = 2; i <= 3 && i <= totalPage; i++) {
                pagelist.add(i);
            }
        } else if (page == totalPage) {
            for (int i = totalPage; i >= totalPage - 2 && i > 1; i--) {
                pagelist.add(i);
            }
            Collections.sort(pagelist);
        } else {
            for (int i = page; i <= page + 1 && i <= totalPage; i++) {
                pagelist.add(i);
            }
            for (int i = page - 1; i >= page - 1 && i > 1; i--) {
                pagelist.add(i);
            }
            Collections.sort(pagelist);
        }
        model.addAttribute("pageList", pagelist);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listData", list.getContent());
		model.addAttribute("currentPage", page);

        return "client/listVocab";
    }

    @GetMapping("/detailVocab")
    public String DetalVocab(@RequestParam int idVocab, Model model) {

    	Optional<BaiTapTuVung> bttuvung = baitaptuvungService.getBaiTuVungById(idVocab);

        List<NoiDungBaiTapTuVung> list = noidungbaitaptuvungService.getListBaiTapTuVung(bttuvung.get());

        List<BaiTapTuVung> baitaptuvung = baitaptuvungService.getBaiTapTuVung(idVocab);
//        List<CommentTuVung> listCmt = cmttuvungService.findByBaiTapTuVung(baitaptuvung.get(0));

//        model.addAttribute("listcomment", listCmt);
        model.addAttribute("idBaiTuVung", list.get(0).getBaitaptuvung().getBaitaptuvungid());
        model.addAttribute("bttuvung", bttuvung.get());
        model.addAttribute("listCauHoi", list);
//        model.addAttribute("countCmt", listCmt.size());
        return "client/VocabDetail";

    }

    @RequestMapping(value = "/searchVocab/{search}", method = RequestMethod.POST)
    public String searchVocab(Model model, @PathVariable("search") String search,
                              @RequestParam(defaultValue = "1") int page) {

        // default value lấy từ kết quả đầu tiên

        if (search.equals("all")) {
            Page<BaiTapTuVung> list = baitaptuvungService.getBaiTapTuVung(page - 1, 4);
            int totalPage = list.getTotalPages();
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("listData", list.getContent());
            model.addAttribute("currentPage", page);

            List<Integer> pagelist = new ArrayList<Integer>();

            // Lap ra danh sach cac trang
            if (page == 1 || page == 2) {
                for (int i = 2; i <= 3 && i <= totalPage; i++) {
                    pagelist.add(i);
                }
            } else if (page == totalPage) {
                for (int i = totalPage; i >= totalPage - 2 && i > 1; i--) {
                    pagelist.add(i);
                }
                Collections.sort(pagelist);
            } else {
                for (int i = page; i <= page + 1 && i <= totalPage; i++) {
                    pagelist.add(i);
                }
                for (int i = page - 1; i >= page - 1 && i > 1; i--) {
                    pagelist.add(i);
                }
                Collections.sort(pagelist);
            }
            model.addAttribute("pageList", pagelist);
            model.addAttribute("search", search);
        } else {
            List<BaiTapTuVung> list = baitaptuvungService.searchListBaiTapTuVung(search);
            model.addAttribute("listData", list);
            model.addAttribute("search", search);
        }
        return "client/resultSearchVocab";
    }	

}
