package czm.demo.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * dbcp连接池使用
 *
 */
public class DbcpPool {

	public static final String DEFAULT_AUTO_COMMIT = "defaultAutoCommit";
	public static final String DEFAULT_READ_ONLY = "defaultReadOnly";
	public static final String DEFAULT_TRANSACTION_ISOLATION = "defaultTransactionIsolation";
	public static final String DEFAULT_CATALOG = "defaultCatalog";
	public static final String INITIAL_SIZE = "initialSize";
	public static final String MAX_ACTIVE = "maxActive";
	public static final String MAX_IDLE = "maxIdle";
	public static final String MIN_IDLE = "minIdle";
	public static final String MAX_WAIT = "maxWait";
	public static final String VALIDATION_QUERY = "validationQuery";
	public static final String TEST_ON_BORROW = "testOnBorrow";
	public static final String TEST_ON_RETURN = "testOnReturn";
	public static final String TEST_WHILE_IDLE = "testWhileIdle";
	public static final String TIME_BETWEEN_EVICTION_RUNS_MILLIS = "timeBetweenEvictionRunsMillis";
	public static final String POOL_PREPARED_STATEMENTS = "poolPreparedStatements";
	public static final String MAX_OPEN_PREPARED_STATEMENTS = "maxOpenPreparedStatements";
	public static final String ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED = "accessToUnderlyingConnectionAllowed";
	public static final String REMOVE_ABANDONED = "removeAbandoned";
	public static final String REMOVE_ABANDONED_TIMEOUT = "removeAbandonedTimeout";

	private static void setPoolProperties(GenericObjectPool pool, Properties properties) {

		String value = properties.getProperty(MAX_ACTIVE);
		if (value != null && !value.isEmpty()) {
			pool.setMaxActive(Integer.valueOf(value));
		}

		value = properties.getProperty(MAX_IDLE);
		if (value != null && !value.isEmpty()) {
			pool.setMaxIdle(Integer.valueOf(value));
		}

		value = properties.getProperty(MIN_IDLE);
		if (value != null && !value.isEmpty()) {
			pool.setMinIdle(Integer.valueOf(value));
		}

		value = properties.getProperty(MAX_WAIT);
		if (value != null && !value.isEmpty()) {
			pool.setMaxWait(Long.valueOf(value));
		}
		value = properties.getProperty(TEST_ON_BORROW);
		if (value != null && !value.isEmpty()) {
			pool.setTestOnBorrow(Boolean.valueOf(value));
		}

		value = properties.getProperty(TEST_ON_RETURN);
		if (value != null && !value.isEmpty()) {
			pool.setTestOnReturn(Boolean.valueOf(value));
		}

		value = properties.getProperty(TEST_WHILE_IDLE);
		if (value != null && !value.isEmpty()) {
			pool.setTestWhileIdle(Boolean.valueOf(value));
		}

		value = properties.getProperty(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
		if (value != null && !value.isEmpty()) {
			pool.setTimeBetweenEvictionRunsMillis(Long.valueOf(value));
		}

	}

	public static void main(String[] args) {
		GenericObjectPool gpool = new GenericObjectPool();

		gpool.setMaxIdle(-1);
		gpool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_GROW);
		gpool.setMaxActive(10);
		String url = "jdbc:mysql://localhost:3306/kettle_rep?defaultFetchSize=500&useCursorFetch=true";
		String userName = "root";
		String password = "123456";

		Properties properties = new Properties();
		properties.setProperty("user", userName);
		properties.setProperty("password", password);
		properties.setProperty("testWhileIdle", "true");
		properties.setProperty("timeBetweenEvictionRunsMillis", "5000");
		properties.setProperty("removeAbandoned", "true");
		properties.setProperty("testOnBorrow", "true");

		setPoolProperties(gpool, properties);
		ConnectionFactory cf = new DriverManagerConnectionFactory(url, properties);
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gpool, null, null, false, false);

		for (int i = 0; i < 5; i++) {
			try {
				gpool.addObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PoolingDriver pd = new PoolingDriver();
		pd.registerPool("test", gpool);

		Scanner scanner = new Scanner(System.in);

		try {
			while (true) {
				String nextLine = scanner.nextLine();
				if (!nextLine.isEmpty()) {
					Connection connection = pd.connect("jdbc:apache:commons:dbcp:" + "test", properties);
					System.out.println(connection.isClosed());
					PreparedStatement prepareStatement = connection.prepareStatement("select count(1) from r_step");
					ResultSet rs = prepareStatement.executeQuery();
					if (rs.next()) {
						System.out.println(System.currentTimeMillis() + ":" + rs.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
