package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerMapping {

	private Map<String,Handler> handlerMap=new HashMap<String, Handler>();
	
	public Handler getHandler(String path) {
		
		return handlerMap.get(path);
		
	}
	
	public void process(List<Object> beans) {

		for(Object bean:beans) {
			Class<?> clz=bean.getClass();
			Method[] methods = clz.getDeclaredMethods();
			for(Method method:methods) {
				RequestMapping annotation = method.getDeclaredAnnotation(RequestMapping.class);
				if(annotation!=null) {
					String value=annotation.value();
					if(value!=null&&value.length()>0) {
						handlerMap.put(value,new Handler(bean,method));
					}
				}
			}
			
		}
		
		
		System.out.println(handlerMap);
				
	}
	
}
