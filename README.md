# smartmvc

## 使用过程

1. 依赖dom4j
	
		<dependency>
			<groupId>dom4j</groupId>
			<artifactId>dom4j</artifactId>
			<version>1.6.1</version>
		</dependency>
2. 将dispacherServlet配置到web.xml中
	
	<!-- -->

		<servlet>
			<servlet-name>dispatcherServlet</servlet-name>
			<servlet-class>base.web.Dispatcher</servlet-class>
			<load-on-startup>1</load-on-startup>
		</servlet>
		<servlet-mapping>
			<servlet-name>dispatcherServlet</servlet-name>
			<url-pattern>*.do</url-pattern>
		</servlet-mapping>

3. 在resources文件夹中编写配置文件smartmvc.xml
	<!-- -->
		<beans>
			<bean class="controller.HelloController">	
			</bean>
		</beans>
4. 创建controller包,在包中创建类HelloController并在其上添加@RequestMapping注解,在注解内配置url,例如@RequestMapping("/hello.do")
		package controller;

		import base.common.RequestMapping;

		public class HelloController extends Controller{
	
		@RequestMapping("/hello.do")
		public String hello() {
			System.out.println(this.getClass().getSimpleName()+": hello");
			
			return "hello";
		}

