package czm.demo.cxf;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

/**
 * 通过cxf发布一个webservice
 * 
 * @author chenzhiming
 *
 */
public class PublishRestService {

	public static void main(String[] args) throws InterruptedException {
		// 使用JaxWsServerFactoryBean发布服务，需要在类上加入@WebService注解，
		// 如果不加，当前类中的方法都不能被发布为web方法

		JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
		// 发布服务的地址
		bean.setAddress("http://192.168.20.19:8080/");
		// 接口类型
		bean.setResourceClasses(UserRestService.class);
		// 接口的实现类
		bean.setResourceProvider(UserRestService.class, new SingletonResourceProvider(new UserRestService()));
		// 发布服务
		bean.create();
	}
}
