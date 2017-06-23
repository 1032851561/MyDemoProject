package czm.demo.jersey;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import czm.demo.common.entity.User;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("user")
public class UserService {

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers() {
		System.out.println("请求到达：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		User user1 = new User(1, "name1", 21);
		User user2 = new User(2, "name2", 22);
		User user3 = new User(3, "name3", 23);
		List<User> users = Arrays.asList(user1, user2, user3);
		return gson.toJson(users);
	}
}