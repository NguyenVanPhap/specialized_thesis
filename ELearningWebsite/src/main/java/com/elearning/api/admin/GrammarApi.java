package com.elearning.api.admin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elearning.entities.Grammar;
import com.elearning.service.GrammarService;

@RestController
@RequestMapping("/api/admin/grammar")
public class GrammarApi {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private GrammarService baigrammarService;

	@GetMapping("/loadGrammar")
	public List<String> showAllGrammar() {

		List<Grammar> list = baigrammarService.getAllGrammar();

		List<String> response = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
//			String json ="{"+"'baithithuid':"+"'"+list.get(i).getBaithithuid()+	"',"
//					+ "'anhbaithithu':'"+list.get(i).getAnhbaithithu()+			"',"
//					+ "'tenbaithithu':'"+list.get(i).getTenbaithithu()+			"'}";

			String json = "baithithuid:" + list.get(i).getGrammarId() + "," + "tenbaithithu:"
					+ list.get(i).getGrammarName();

			response.add(json);
		}

		return response;

	}

	@RequestMapping(value = "/delete/{idBaiGrammar}")
	public String deleteById(@PathVariable("idBaiGrammar") int id) {
		baigrammarService.delete(id);
		return "success";
	}

	// get info Grammar ->edit Grammar
	@RequestMapping(value = "/infoGrammar/{idBaiGrammar}")
	public String infoGrammarById(@PathVariable("idBaiGrammar") int id) {
		Grammar baiGrammar = baigrammarService.getGrammar(id).get(0);

		String json = "name==" + baiGrammar.getGrammarName() + "|" + "content==" + baiGrammar.getContentMarkDown();

		return json;
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public List<String> addBaiGrammar(@RequestParam("name") String name,
			@RequestParam("contentMarkdown") String contentMarkdown, @RequestParam("contentHtml") String contentHtml) {
		List<String> response = new ArrayList<String>();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Grammar baigrammar = new Grammar();
		baigrammarService.save(baigrammar);
		try {
			baigrammar.setTenbaigrammar(name);
			baigrammar.setContentMarkDown(contentMarkdown);
			baigrammar.setContentHTML(contentHtml);
			baigrammarService.save(baigrammar);

		} catch (Exception e) {
			response.add(e.toString());
			System.out.println("ErrorAddGrammar:" + e);
		}

		return response;
	}

	@PostMapping(value = "/update")
	@ResponseBody
	public List<String> updateBaiGrammar(@RequestParam("idGrammar") int id, @RequestParam("name") String name,
			@RequestParam("contentMarkdown") String contentMarkdown, @RequestParam("contentHtml") String contentHtml) {

		List<String> response = new ArrayList<String>();
		Grammar baigrammar = baigrammarService.getGrammar(id).get(0);
		baigrammarService.save(baigrammar);
		try {
			baigrammar.setTenbaigrammar(name);
			baigrammar.setContentMarkDown(contentMarkdown);
			baigrammar.setContentHTML(contentHtml);

			baigrammarService.save(baigrammar);

		} catch (Exception e) {
			response.add(e.toString());
			System.out.println("ErrorAddGrammar:" + e);

		}

		return response;
	}

}
