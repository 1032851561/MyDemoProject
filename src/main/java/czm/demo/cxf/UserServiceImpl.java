package czm.demo.cxf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.gtland.dataservice.api.dto.PropertyFilter;
import com.gtland.dataservice.entity.Pager;

import czm.demo.common.entity.User;

@WebService(endpointInterface = "czm.demo.cxf.UserService", serviceName = "UserService")
public class UserServiceImpl implements UserService {
	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public String getUser() {
		System.out.println("请求到达：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 1000; i++) {
			users.add(new User(i, "name" + i, i));
		}
		return gson.toJson(users);
	}

	public String getUserPage(@WebParam(name = "limit") int limit, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAll(int limit, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPage(Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getByPropertyFilter(List<PropertyFilter> listPropertyFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPageByPropertyFilter(List<PropertyFilter> listPropertyFilter, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

}
