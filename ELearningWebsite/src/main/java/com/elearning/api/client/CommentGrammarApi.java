package com.elearning.api.client;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.elearning.dto.CommentGrammarDto;
import com.elearning.entities.CommentGrammar;
import com.elearning.entities.Grammar;
import com.elearning.entities.NguoiDung;
import com.elearning.entities.ResponseObject;
import com.elearning.repository.NguoiDungRepository;
import com.elearning.service.CmtGrammarService;
import com.elearning.service.CommentGrammarService;
import com.elearning.service.GrammarService;

@RestController
@RequestMapping("/api/comment/grammar")
@SessionAttributes("loggedInUser")
public class CommentGrammarApi {

	@Autowired
	CommentGrammarService commentGrammarService;
	
	@Autowired
	CmtGrammarService cmtGrammarSerive;
	@Autowired
	GrammarService grammarService;
	
	@Autowired
	NguoiDungRepository nguoidungRepository;

	@GetMapping("/id={grammarId}")
	public ResponseEntity<Object> getListreadingQuestionByReadingExerciseId(@RequestParam(defaultValue = "1") int page,
			@PathVariable Integer grammarId) {

		Grammar objGrammar = grammarService.getInfor(grammarId);
		return ResponseEntity.ok(commentGrammarService.findByGrammar(objGrammar));
	}

	public NguoiDung getSessionUser(HttpServletRequest request) {
		NguoiDung nguoiDung = (NguoiDung) request.getSession().getAttribute("loggedInUser");
		System.out.println("listcomment: " + nguoiDung);
		return nguoiDung;
		
	}

	@PostMapping("/add/{contentComment}/{grammarId}")
	public ResponseEntity<Object> add(@RequestBody CommentGrammar commentGrammar, HttpServletRequest request, @PathVariable("contentComment") String contentComment, 
			@PathVariable("grammarId") Integer grammarId) {
//		NguoiDung nguoiDung = getSessionUser(request);
		NguoiDung nguoiDung= this.nguoidungRepository.getById((long) 2);

		Grammar grammar = grammarService.getInfor(grammarId);
		
		Date time = new Date();
		
		System.out.println("listcomment: " + nguoiDung);
		
		commentGrammar.setUserName(nguoiDung.getHoTen());
		commentGrammar.setGrammar(grammar);
		commentGrammar.setCommentDate(time);
		commentGrammar.setContent(contentComment);
		
		return ResponseEntity.ok(commentGrammarService.save(commentGrammar));
	}
//	@PostMapping("/add-comment")
//	public ResponseEntity<Object> addComment(CommentGrammar commentGrammar, @RequestBody CommentGrammarDto cmtdto, @RequestParam("grammarId") int grammarId, @RequestParam("content") String content) {
////		NguoiDung nguoiDung = getSessionUser(request)
//		NguoiDung nguoiDung= this.nguoidungRepository.getById((long) 2);
//
//		Grammar grammar = grammarService.getInfor(grammarId);
//		System.out.println("grammarId: " + grammar);
//		Date time = new Date();
////		String content = cmtdto.getContent();
//		System.out.println("listcomment: " + nguoiDung);
////		try {
////			
////			commentGrammar.setUserName(nguoiDung.getHoTen());
//////			CommentGrammar comment = this.commentGrammarService.save(content);
////			return ResponseEntity.ok(commentGrammarService.save(commentGrammar));
////		}catch (Exception ex) {
////			ex.printStackTrace();
////		}
////		
////		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		commentGrammar.setUserName(nguoiDung.getHoTen());
//		commentGrammar.setGrammar(grammar);
//		commentGrammar.setCommentDate(time);
//		commentGrammar.setContent(content);
//		System.out.println("New Comment: "+ commentGrammar);
//		return ResponseEntity.ok(commentGrammarService.save(commentGrammar));	
//	}
	@PostMapping("/add-comment")
	public ResponseObject addComment(@RequestBody @Valid CommentGrammar newComment, BindingResult result, HttpServletRequest request) {
		
//		NguoiDung nguoiDung = getSessionUser(request);
		NguoiDung nguoiDung= this.nguoidungRepository.getById((long) 2);
				
		ResponseObject ro = new ResponseObject();
		
		if(result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			ro.setErrorMessages(errors);
			ro.setStatus("fail");
		}else {
			newComment.setUserName(nguoiDung.getHoTen());
			commentGrammarService.save(newComment);
			ro.setData(newComment);
			ro.setStatus("success");
		}
		System.out.println("id comment: "+ newComment.getId());
		System.out.println("comment: "+ ro.getData());
		return ro;
	}
	
	
}
