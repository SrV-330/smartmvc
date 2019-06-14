package controller;

import base.common.RequestMapping;

public class HelloController extends Controller{
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println(this.getClass().getSimpleName()+": hello");
		
		return "hello";
	}

	
	
	
}
