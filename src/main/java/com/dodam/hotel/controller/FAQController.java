package com.dodam.hotel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodam.hotel.dto.api.ResponseMsg;
import com.dodam.hotel.handler.exception.ManagerCustomRestFullException;
import com.dodam.hotel.repository.model.FAQ;
import com.dodam.hotel.repository.model.Manager;
import com.dodam.hotel.service.ManagerFAQService;
import com.dodam.hotel.util.Define;

@Controller
public class FAQController {
	@Autowired
	private ManagerFAQService managerFAQService;
	@Autowired
	private HttpSession session;

	// 매니저용
	@GetMapping("/manager/faq")
	public String faqPage(Model model) {
		List<FAQ> responseFaqs = managerFAQService.readAllFAQ();
		responseFaqs.stream().forEach(e -> {
			e.setContent(e.getContent().replace("\r\n", "<br>"));
		});
		model.addAttribute("faqList", responseFaqs);
		return "/manager/FAQ";
	}

	// 매니저용
	@GetMapping("/manager/faq/write")
	public String faqWritePage() {
		return "/manager/FAQWrite";
	}

	// 매니저용
	@PostMapping("/manager/faq/write-proc")
	public String faqWriteProc(@Validated FAQ faq, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new ManagerCustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		managerFAQService.createFAQ(faq);
		return "redirect:/manager/faq";
	}

	// 매니저용
	@GetMapping("/manager/faq/update/{id}")
	public String faqUpdatePage(@PathVariable Integer id, Model model) {
		if (id == null) {
			throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		FAQ responseFaq = managerFAQService.readFAQById(id);
		model.addAttribute("faq", responseFaq);
		return "/manager/FAQUpdate";
	}

	// 매니저용
	@PostMapping("/manager/faq/update-proc")
	public String faqUpdateProc(@Validated FAQ faq, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new ManagerCustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		managerFAQService.updateFAQ(faq);
		return "redirect:/manager/faq";
	}

	// 매니저용
	@DeleteMapping("/manager/delete/faq")
	@ResponseBody
	public ResponseMsg faqDeleteProc(Integer id) {
		if (id == null) {
			throw new ManagerCustomRestFullException("아이디가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		Manager manager = (Manager) session.getAttribute(Define.MANAGERPRINCIPAL);
		if (manager == null) {
			ResponseMsg failMsg = ResponseMsg.builder().status_code(HttpStatus.FORBIDDEN.value()).msg("사용 인증 권한이 없습니다")
					.redirect_uri("/manager/faq/" + id).build();
			return failMsg;
		}
		managerFAQService.deleteFAQ(id);

		ResponseMsg successMsg = ResponseMsg.builder().status_code(HttpStatus.OK.value()).msg("삭제완료되었습니다")
				.redirect_uri("/manager/faq").build();

		return successMsg;
	}
}
