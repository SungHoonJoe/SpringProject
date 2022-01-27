package com.honsta.project.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/signup_view")
	public String signUpView() {
		return "user/signUp";
	}
	
	@GetMapping("/signin_view")
	public String signInView() {
		return "user/signIn";
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		//세션 회원 정보 제거
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("name");
		
		return "redirect:/user/signin_view";
		
	}
	
	

}
