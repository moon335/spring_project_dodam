package com.dodam.hotel.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodam.hotel.dto.InquiryRequestDto;
import com.dodam.hotel.dto.UserRequestDto;
import com.dodam.hotel.dto.UserResponseDto;
import com.dodam.hotel.dto.api.ResponseMsg;
import com.dodam.hotel.handler.exception.CustomRestFullException;
import com.dodam.hotel.repository.model.Coupon;
import com.dodam.hotel.repository.model.Event;
import com.dodam.hotel.repository.model.Grade;
import com.dodam.hotel.repository.model.GradeInfo;
import com.dodam.hotel.repository.model.Membership;
import com.dodam.hotel.repository.model.Reply;
import com.dodam.hotel.repository.model.User;
import com.dodam.hotel.service.CouponService;
import com.dodam.hotel.service.EventService;
import com.dodam.hotel.service.GradeService;
import com.dodam.hotel.service.QuestionService;
import com.dodam.hotel.service.UserService;
import com.dodam.hotel.util.CreateRandomStr;
import com.dodam.hotel.util.Define;
import com.dodam.hotel.util.PagingObj;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private EventService eventService;

	// 메인 페이지 (성희)
	@GetMapping({ "", "/" })
	public String mainPage(Model model) {
		List<Event> events = eventService.readAllOnGoingEvent();
		model.addAttribute("events", events);
		return "/main";
	}

	// 로그인 페이지 (현우)
	@GetMapping("/login")
	public String loginPage() {
		return "/user/login";
	}

	// 내정보 페이지 (현우)
	@GetMapping("/myPage")
	public String myPage(Model model) {
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto) session
				.getAttribute(Define.PRINCIPAL);
		List<Coupon> responseCoupons = couponService.readByUserId(principal.getId());
		// 등급 정보 불러오기
		GradeInfo responseGrade = gradeService.readGradeByUserId(principal.getId());
		// 포인트 정보 불러오기
		Integer point = userService.readPointInfo(principal.getId());
		DecimalFormat df = new DecimalFormat("###,###");
		String formatPoint = df.format(point);
		// 회원 정보 불러오기
		UserResponseDto.MyPageResponseDto responseUser = userService.readUserByEmail(principal.getEmail());
		model.addAttribute("responseGrade", responseGrade);
		model.addAttribute("coupons", responseCoupons);
		model.addAttribute("point", formatPoint);
		model.addAttribute("responseUser", responseUser);
		return "/user/myPage";
	}
	// 로그인 기능 구현 (현우)
	@PostMapping("/loginProc")
	public String loginProc(@Validated UserRequestDto.LoginFormDto loginDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		UserResponseDto.LoginResponseDto principal = userService.readUserByIdAndPassword(loginDto);
		if(principal == null) {
			throw new CustomRestFullException("아이디/비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST);
		}
		session.setAttribute(Define.PRINCIPAL, principal);
		session.setAttribute("tel", principal.getTel());
		if (principal.getRandomPwdStatus()) {
			return "/user/changePw";
		}

		return "redirect:/";
	}
	
	@GetMapping("/changePw")
	public String changePw() {
		return "/user/changePw";
	}

	// 비밀번호 변경 페이지
	@PostMapping("/changePwProc")
	public String changePwProc(@Validated UserRequestDto.UpdatePwdDto pwdDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		// 앞에서 비밀번호 확인 처리
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto) session
				.getAttribute(Define.PRINCIPAL);
		userService.updateOnlyPw(pwdDto.getChangePwd(), principal.getId());
		return "redirect:/logout";
	}

	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

	// 회원정보 수정 처리 (김현우)
	@PostMapping("/myPageProc")
	public String myPageProc(@Validated UserRequestDto.MyPageFormDto myPageDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto) session
				.getAttribute(Define.PRINCIPAL);
		UserResponseDto.LoginResponseDto responseUser = userService.updateUser(myPageDto);

		// 비밀번호 수정 시, DB 비밀번호랑 맞는지 확인 (암호화 처리 예정) - 다를경우 바뀐 비밀번호로 세팅
		if (principal.getPassword() != responseUser.getPassword()) {
			principal.setPassword(myPageDto.getPassword());
			session.setAttribute(Define.PRINCIPAL, responseUser);
		}

		return "redirect:/myPage";
	}

	// 회원가입 페이지 (성희)
	@GetMapping("/join")
	public String joinPage() {
		return "/user/join";
	}

	// 회원가입 처리 (성희)
	@PostMapping("/join")
	public String join(@Validated UserRequestDto.InsertDto insertDto, BindingResult bindingResult) {
		if(insertDto.getBirth() == null) {
			throw new CustomRestFullException("생일을 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		String address = insertDto.getAddress() + " " + insertDto.getDetailAddress();
		insertDto.setAddress(address);
		userService.createUser(insertDto);
		return "redirect:/";
	}
	
	// 회원 탈퇴 처리 (성희)
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseMsg withdraw(String email) {
		int result = userService.deleteUser(email);
		if(result != 1) {
			ResponseMsg failMsg = ResponseMsg.builder()
					.status_code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.msg("다시 시도해주세요")
					.redirect_uri("/myPage")
					.build();
			return failMsg;
		} else {
			ResponseMsg successMsg = ResponseMsg.builder()
					.status_code(HttpStatus.OK.value())
					.msg("탈퇴 처리 하였습니다. 감사합니다")
					.redirect_uri("/logout")
					.build();
			return successMsg;
		}
	}
	
	// 카카오 회원가입 처리 (성희)
	@PostMapping("/kakaoJoin")
	public String kakaoJoin(@Validated UserRequestDto.InsertDto insertDto, BindingResult bindingResult) {
		if(insertDto.getBirth() == null) {
			throw new CustomRestFullException("생일을 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		insertDto.setSocialLogin(true);
		userService.createUserKakao(insertDto);
		return "redirect:/";
	}

	// 멤버쉽 페이지 (성희)
	@GetMapping("/membership")
	public String membershipPage(Model model) {
		List<Grade> gradeList = gradeService.readAllGrade();
		model.addAttribute("gradeList", gradeList);
		Membership membership = userService.readMembershipInfo();
		model.addAttribute("membership", membership);
		return "/membership/membership";
	}

	// 멤버쉽 가입 처리 (성희)
	@GetMapping("/joinMembership")
	public String membership() {
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto) session
				.getAttribute(Define.PRINCIPAL);
		UserResponseDto.MyPageResponseDto responseUser = userService.readUserByEmail(principal.getEmail());
		userService.createMembership(responseUser.getId());
		return "redirect:/";
	}

	// 아이디, 비밀번호 찾기
	@GetMapping("/findIdPw")
	public String findIdPw() {
		return "/user/inquiry";
	}

	// id 찾기 기능
	@PostMapping("/idInquiry")
	public String idInquiry(@Validated InquiryRequestDto.IdInquiryRequestDto idInquiryRequestDto, BindingResult bindingResult, Model model) {
		if(idInquiryRequestDto.getBirth() == null) {
			throw new CustomRestFullException("생일을 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}
		User responseUser = userService.readUserForIdInquiry(idInquiryRequestDto);
		model.addAttribute("responseUser", responseUser);
		return "/user/idInquiryPage";
	}

	// pw 찾기 페이지 이동
	@GetMapping("/pwInquiryPage")
	public String pwInquiryPage() {
		return "/user/pwInquiryPage";
	}

	// pw 찾기 기능
	@PostMapping("/pwInquiry")
	public String pwInquiry(@Validated InquiryRequestDto.PwInquiryRequestDto pwInquiryRequestDto, BindingResult bindingResult) {
		if(pwInquiryRequestDto.getBirth() == null) {
			throw new CustomRestFullException("생일을 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> {
				throw new CustomRestFullException(e.getDefaultMessage(), HttpStatus.BAD_REQUEST);
			});
		}

		int resultRow = userService.updatePwByUserInfo(pwInquiryRequestDto);

		return "redirect:/pwInquiryPage";
	}

	// 유저가 쓴 reply 확인 - 현우
	@GetMapping("/myReplys")
	public String myReply(Model model,
			@RequestParam(name = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(name = "cntPerPage", defaultValue = "5", required = false) String cntPerPage) {
		UserResponseDto.LoginResponseDto principal = (UserResponseDto.LoginResponseDto) session
				.getAttribute(Define.PRINCIPAL);

		int total = questionService.readQuestionCountByUserId(principal.getId());

		PagingObj po = new PagingObj(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

		List<Reply> responseQuestions = questionService.readQuestionByUserIdPaging(po, principal.getId());
		model.addAttribute("paging", po);
		model.addAttribute("questions", responseQuestions);
		return "/user/replyList";
	}

} // end of class
