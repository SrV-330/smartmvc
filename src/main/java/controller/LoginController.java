package controller;

import base.common.RequestMapping;

public class LoginController extends Controller{
	
	@RequestMapping("/tologin.do")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/login.do")
	public String login() {
		
		return "redirect:success.do";
	}
	@RequestMapping("/success.do")
	public String success() {
		
		return "success";
	}
}
