package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.common.RequestMapping;

public class LoginController extends Controller{

	@RequestMapping("/tologin.do")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/login.do")
	public String login(HttpServletRequest req,HttpServletResponse resp) {
		String name=req.getParameter("username");
		String pwd=req.getParameter("password");

		if("zhangsan".equals(name)&&"123".equals(pwd)) {


			return "redirect:success.do";
		}else {
			req.setAttribute("err_msg", "login faild");
			return "login";
		}
	}
	@RequestMapping("/success.do")
	public String success() {

		return "success";
	}
}
