package czm.demo.jersey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 */
@Path("user")
public class UserRestService {

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers() {
		System.out.println("请求到达：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		List<User> users = new ArrayList<User>();
		for (int i = 1; i < 100; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}
}