package czm.demo.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultInvokeHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(
				String.format("代理对象:%s,参数:%s,参数:%s", proxy.getClass().getName(), method.getName(), args.toString()));
		String result = "";
		for (Object object : args) {
			result = result + " " + object.toString();
		}
		return result;
	}

}
