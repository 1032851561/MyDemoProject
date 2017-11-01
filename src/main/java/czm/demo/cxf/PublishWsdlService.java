package czm.demo.cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 通过cxf发布一个webservice
 * 
 * @author chenzhiming
 *
 */
public class PublishWsdlService {

	public static void main(String[] args) throws InterruptedException {
		// 使用JaxWsServerFactoryBean发布服务，需要在类上加入@WebService注解，
		// 如果不加，当前类中的方法都不能被发布为web方法
		
		JaxWsServerFactoryBean bean = new JaxWsServerFactoryBean();
		// 发布服务的地址
		bean.setAddress("http://192.168.20.19:7070/UserService");
		// 接口类型
		bean.setServiceClass(UserService.class);
		// 接口的实现类
		bean.setServiceBean(new UserServiceImpl());
		// 发布服务
		bean.create();
	}
}
