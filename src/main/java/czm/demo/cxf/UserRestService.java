package czm.demo.cxf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import czm.demo.common.entity.DriverName;
import czm.demo.common.entity.User;
import czm.demo.spring.jdbc.JdbcTemplateFactory;

/**
 */
@Path("/")
public class UserRestService {

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers() {
		System.out.println("请求到达1：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 10; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("username") String userName) {
		System.out.println("请求到达2：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 10; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}

	@GET
	@Path("/user")
	@Produces("text/plain")
	public String getUser(@QueryParam("name") String name, @QueryParam("age") int age) {
		System.out.println("请求到达3：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		System.out.println("name=" + name + ",age=" + age);
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 10; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}

	@POST
	@Produces("text/plain")
	public String addUser(@QueryParam("name") String name, @QueryParam("age") int age) {
		System.out.println("请求到达4：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		System.out.println("name=" + name + ",age=" + age);
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 10; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}

	/**
	 * body entity传参
	 * 
	 * @param text
	 * @return
	 */
	// @POST
	// @Produces("text/plain")
	// public String addUser(String text) {
	// System.out.println("请求到达5：" + new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").format(new Date(System.currentTimeMillis())));
	// List<User> users = new ArrayList<User>();
	// for (int i = 1; i < 10; i++) {
	// users.add(new User(i, "name" + i, i));
	// }
	// return gson.toJson(users);
	// }

	@POST
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String addUser1(String text, @QueryParam("test") String test) {
		System.out.println("请求到达6：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 10; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}

	@POST
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String addUsers(@FormParam("name") String name) {
		System.out.println("请求到达7：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 10; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}

	@POST
	@Path("/getByAccountCreateTime")
	@Produces("text/plain")
	public String getByAccountCreateTime(@QueryParam("createTime") String createTime) {
		System.out.println("getByAccountCreateTime:"+createTime);
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate("jdbc:mysql://192.168.20.125:3306/utopa?transformedBitIsBoolean=true", "root", "123456", DriverName.MYSQL);
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from sc_acc_account where CREATE_TIME > ? ", createTime);
		System.out.println("getByAccountCreateTime返回结果数:"+list.size());
		return gson.toJson(list);
	}

	@POST
	@Path("/getCouponByCreateTime")
	@Produces("text/plain")
	public String getCouponByCreateTime(@QueryParam("createTime") String createTime) {
		System.out.println("getCouponByCreateTime:"+createTime);
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate("jdbc:mysql://192.168.20.125:3306/utopa?transformedBitIsBoolean=true", "root", "123456", DriverName.MYSQL);
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from ut_prooffline_coupon where CREATE_TIME > ? ", createTime);
		System.out.println("getCouponByCreateTime返回结果数:"+list.size());
		return gson.toJson(list);
	}

}