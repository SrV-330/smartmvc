package controller;

import base.common.RequestMapping;

public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println(this.getClass().getSimpleName()+": hello");
		
		return "hello";
	}

}
