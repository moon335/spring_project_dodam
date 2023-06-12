package com.dodam.hotel.controller;

import java.io.File;



import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dodam.hotel.dto.QuestionRequestDto.InsertQuestionRequestDto;
import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.repository.model.FAQ;
import com.dodam.hotel.repository.model.Question;
import com.dodam.hotel.service.QuestionService;
import com.dodam.hotel.util.Define;
import com.dodam.hotel.util.PagingObj;

/**
 * 
 * @author 현우
 * faq, 문의 controller
 *
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private HttpSession session;
	
	// faq로 이동
	@GetMapping("/question")
	public String questionPage(Model model) {
		List<FAQ> responseFaqs = questionService.readAllFaq();
		responseFaqs.stream().forEach(e -> {
			e.setContent(e.getContent().replace("\r\n", "<br>"));
		});
		model.addAttribute("faqList", responseFaqs);
		return "/user/question";
	}
	
	// qna 페이지 이동 (현우)
	@GetMapping("/qnaPage")
	public String qnaPage() {
		return "/user/qna";
	}
	
	// qna 등록 처리 - 유저
	@PostMapping("/qnaProc")
	public String qnaProc(@Validated InsertQuestionRequestDto question, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto)session.getAttribute(Define.PRINCIPAL);
		
		question.setUserId(principal.getId());
		
		MultipartFile file = question.getFile();
		if(file.isEmpty() ==false) {
			
			if (file.getSize() > Define.MAX_FILE_SIZE) {
				throw new CustomRestFullException("파일 크기가 50MB 이상일 수 없습니다.", HttpStatus.BAD_REQUEST);
			}
			
			// 파일 최대 크기 지정 -- 추후 추가 예정
			try {
				String saveDirectory = Define.UPLOAD_DIRECTORY;
				File dir = new File(saveDirectory);
				
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				
				UUID uuid = UUID.randomUUID();
				String fileName = uuid + "_" + file.getOriginalFilename();
				String uploadPath = Define.UPLOAD_DIRECTORY + File.separator + fileName;
				
				File destination = new File(uploadPath);
				file.transferTo(destination);
				
				question.setOriginFile(file.getOriginalFilename());
				question.setUploadFile(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomRestFullException("파일 업로드 실패", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		questionService.createQuestion(question);
		return "redirect:/";
	}
	
	//문의사항 게시판 - 매니저
	@GetMapping("/questionList")
	public String questionList(Model model, 
			@RequestParam(name = "nowPage", defaultValue = "1" , required = false) String nowPage, 
			@RequestParam(name = "cntPerPage", defaultValue = "5" , required = false) String cntPerPage) {
		//주소 요청시 작성된 계시물 제목 List 로 다불러오기
		int total = questionService.readAllQuestionCount();
		PagingObj obj = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		List<Question> responseQuestions = questionService.readAllQuestionList(obj);
		if(responseQuestions != null) {
			model.addAttribute("paging", obj);
			model.addAttribute("questionList", responseQuestions);
		}
		return "/board/question";
	}
	
	//문의 사항 상세보기 - 매니저
	@GetMapping("/questionDetail/{id}")
	public String questionDetail(@PathVariable Integer id,Model model) {
		if(id == null) {
    		throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		Question responseQuestion = questionService.readById(id);
		if(responseQuestion != null) {
			model.addAttribute("principal", session.getAttribute(Define.MANAGERPRINCIPAL));
			model.addAttribute("question", responseQuestion);
		}
		return "/board/questionDetail";
	}
	
	//댓글 달아 보내기 - 매니저
	@PostMapping("/reply/{questionId}/{managerId}")
	@Transactional
	public String insertReply(String content,@PathVariable Integer questionId,@PathVariable Integer managerId) {
		if(questionId == null) {
    		throw new ManagerCustomRestFullException("questionId가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		if(managerId == null) {
			throw new ManagerCustomRestFullException("managerId가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		questionService.updateStatusById(questionId);
		questionService.insertReply(content, questionId, managerId);
		return "redirect:/question/questionList";
	}
	
	//질문 삭제
	@GetMapping("/questionDelete/{questionId}")
	public String questionDelete(@PathVariable Integer questionId){
		if(questionId == null) {
    		throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
    	}
		questionService.deleteQuestionById(questionId);
		return "redirect:/question/questionList";
	}
	
	// 매니저
	@GetMapping("/category")
	public String questionCategory(String category,Model model) {
		List<Question> responseQuestions = questionService.readByCategory(category);
		if(responseQuestions != null) {
			model.addAttribute("questionList", responseQuestions);
		}
		return "/board/question";
	}
	
	@GetMapping("/chatRoom")
	public String chatPage() {
		return "/webSocket/chat";
	}
	
}
