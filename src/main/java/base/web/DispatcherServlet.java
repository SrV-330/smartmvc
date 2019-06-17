package base.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import base.common.Handler;
import base.common.HandlerMapping;

public class DispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HandlerMapping handlerMapping;
	
	
	@Override
	public void init() throws ServletException {
		
		SAXReader reader=new SAXReader();
		InputStream in=getClass()
				.getClassLoader()
				.getResourceAsStream("smartmvc.xml");
		List<Object> beans = new ArrayList<Object>();
		
		try {
			
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> eles = root.elements();
			for(Element ele:eles) {
				
				String className = ele.attributeValue("class");
				System.out.println("ClassName: "+className);
				Object bean = Class.forName(className).newInstance();
				beans.add(bean);
				
			}
			System.out.println("beans: "+beans);
			
			handlerMapping=new HandlerMapping();
			handlerMapping.process(beans);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}




	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path=req.getRequestURI();
		String contextPath=req.getContextPath();
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL().toString());
		path=path.substring(path.lastIndexOf("/"));
		System.out.println("path: "+path);
		String view="";
		Handler handler = handlerMapping.getHandler(path);
		try {
			if(handler!=null) {
				
				Method method=handler.getMethod();
				Parameter[] params=method.getParameters();
				if(params==null||params.length==0) {
					view=method.invoke(handler.getObject()).toString();
				}else {
					Object[] paramObjs=new Object[params.length];
					for(int i=0;i<params.length;i++) {
						if(params[i].getType()==HttpServletRequest.class) {
							paramObjs[i]=req;
						}else if(params[i].getType()==HttpServletResponse.class){
							paramObjs[i]=resp;
						}
						
					}
					view=method.invoke(handler.getObject(),paramObjs).toString();
				}
				
				System.out.println("view: "+view);
				if(view.startsWith("redirect:")) {
					view=view.substring("redirect:".length());
					resp.sendRedirect(contextPath+"/"+view);
				}else {
					req.getRequestDispatcher("/WEB-INF/"+view+".jsp").forward(req, resp);
				}
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
	}

	
	
}
