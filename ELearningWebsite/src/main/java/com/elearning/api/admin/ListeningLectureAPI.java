package com.elearning.api.admin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.elearning.entities.ListeningLecture;
import com.elearning.helper.ApiRes;
import com.elearning.service.ListeningLectureService;

@RestController
@RequestMapping("/api/admin/listeninglecture")
public class ListeningLectureAPI {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ListeningLectureService listeninglectureService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET, consumes = "application/json", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> getall() {
		return ResponseEntity.ok(listeninglectureService.getAll());
	}

	@RequestMapping(value = "/delete/{idBailisteninglecture}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> deleteById(@PathVariable("listeningLectureId") int id) {
		return ResponseEntity.ok(listeninglectureService.delete(id));

	}

	@RequestMapping(value = "/infolisteninglecture/{idBailisteninglecture}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> getinfor(@PathVariable("listeningLectureId") int id) {
		return ResponseEntity.ok(listeninglectureService.getInfor(id));
	}

	@RequestMapping(value = "/add/{listeninglectureId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> addBailisteninglecture(@RequestParam("listeninglectureName") String name,
			@RequestParam("contentMarkdown") String contentMarkdown, @RequestParam("contentHtml") String contentHtml,
			@RequestPart(value = "fileImage", required = false) MultipartFile file_image) {
		// List<String> response = new ArrayList<String>();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		ListeningLecture objlisteninglecture = new ListeningLecture();
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			if (file_image != null) {
				Path pathImage = Paths.get(rootDirectory + "/static/file/images/grammar/" + ""
						+ objlisteninglecture.getId() + "." + file_image.getOriginalFilename());
				String localPath = "/static/file/images/grammar/" + "" + objlisteninglecture.getId() + "."
						+ file_image.getOriginalFilename();
				file_image.transferTo(new File(pathImage.toString()));
				objlisteninglecture.setFileName(file_image.getOriginalFilename());
				objlisteninglecture.setFilePath(localPath);
			}
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
			return ResponseEntity.ok(apiRes);
		}
		objlisteninglecture.setName(name);
		objlisteninglecture.setContentMarkDown(contentMarkdown);
		objlisteninglecture.setContentHTML(contentHtml);
		return ResponseEntity.ok(listeninglectureService.save(objlisteninglecture));
	}

	/*
	 * @RequestMapping(value = "/update/{idBailisteninglecture}", method =
	 * RequestMethod.POST, consumes = "application/json", produces =
	 * "application/json; charset=utf-8") public List<String>
	 * update(@RequestParam("idlisteninglecture") int id,
	 * 
	 * @RequestParam("name") String name, @RequestParam("contentMarkdown") String
	 * contentMarkdown,
	 * 
	 * @RequestParam("contentHtml") String contentHtml,
	 * 
	 * @RequestPart(value = "fileImage", required = false) MultipartFile file_image)
	 * { List<String> response = new ArrayList<String>(); String rootDirectory =
	 * request.getSession().getServletContext().getRealPath("/"); listeninglecture
	 * bailisteninglecture = listeninglectureService.getInfor(id);
	 * listeninglectureService.save(bailisteninglecture); try {
	 * 
	 * if (file_image != null) { Path pathImage = Paths.get(rootDirectory +
	 * "/static/file/images/listeninglecture/" + "" +
	 * bailisteninglecture.getlisteninglectureId() + "." +
	 * file_image.getOriginalFilename()); String localPath =
	 * "/static/file/images/listeninglecture/" + "" +
	 * bailisteninglecture.getlisteninglectureId() + "." +
	 * file_image.getOriginalFilename(); file_image.transferTo(new
	 * File(pathImage.toString()));
	 * bailisteninglecture.setFileName(file_image.getOriginalFilename());
	 * bailisteninglecture.setFilePath(localPath); }
	 * bailisteninglecture.setlisteninglectureName(name);
	 * bailisteninglecture.setContentMarkDown(contentMarkdown);
	 * bailisteninglecture.setContentHTML(contentHtml);
	 * 
	 * listeninglectureService.save(bailisteninglecture);
	 * 
	 * } catch (Exception e) { response.add(e.toString());
	 * System.out.println("ErrorAddlisteninglecture:" + e);
	 * 
	 * }
	 * 
	 * return response; }
	 */
}
