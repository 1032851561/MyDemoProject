package czm.demo.jersey;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.simple.container.SimpleServerFactory;

/**
 * 通过jersey生成一个restful风格webservice
 * PS：使用jersy发布服务时与cxf相关依赖有冲突，尚未解决，可以注释掉cxf相关pom再运行
 *
 */
public class PublishRestService {
	public static void main(String[] args) throws Exception {

		java.io.Closeable server = null;

		try {
			// Creates a server and listens on the address below.
			// Scans classpath for JAX-RS resources
			PackagesResourceConfig config = new PackagesResourceConfig("czm.demo.jersey");
			server = SimpleServerFactory.create("http://localhost:5555", config);

			System.out.println("Press any key to stop the service...");
			System.in.read();
		} finally {
			try {
				if (server != null) {
					server.close();
				}
			} finally {
				;
			}
		}
	}
}
