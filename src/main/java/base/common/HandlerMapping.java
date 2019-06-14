package base.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerMapping {

	private Map<String,Handler> handlerMap=new HashMap<String, Handler>();
	
	private Handler getHandler(String path) {
		
		return handlerMap.get(path);
		
	}
	
	public void process(List<Object> beans) {

		
		
	}
	
}
