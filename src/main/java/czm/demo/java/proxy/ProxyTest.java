package czm.demo.java.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		DefaultInvokeHandler defaultInvokeHandler = new DefaultInvokeHandler(); 
		MyInterface myInterfaceInstance = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(), new Class[] { MyInterface.class },
				defaultInvokeHandler);//生成接口MyInterface的代理对象，接口的所有方法都会被DefaultInvokeHandler.invoke处理
		String result = myInterfaceInstance.say("hello", "world");
		System.out.println(result);
	}
}
