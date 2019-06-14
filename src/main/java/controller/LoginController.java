package controller;

import base.common.RequestMapping;

public class LoginController extends Controller{
	
	@RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/login")
	public String login() {
		
		return "success";
	}
}
