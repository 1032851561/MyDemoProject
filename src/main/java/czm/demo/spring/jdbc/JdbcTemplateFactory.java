/**
 * 
 */
/**
 * @author chenzhiming
 *
 */
package czm.demo.spring.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcTemplateFactory {

	public static JdbcTemplate getJdbcTemplate(String url, String username, String password, String driver) {
		DruidDataSource dds = new DruidDataSource();
		dds.setUrl(url);
		dds.setUsername(username);
		dds.setPassword(password);
		dds.setDriverClassName(driver);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dds);
		return jdbcTemplate;

	}
}