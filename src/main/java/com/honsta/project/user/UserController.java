package com.honsta.project.user;

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
	
	

}
