package czm.demo.cxf;

import java.util.List;

import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentationCollection;

import com.gtland.dataservice.api.dto.PropertyFilter;
import com.gtland.dataservice.entity.Pager;

@WebService
@WSDLDocumentationCollection({ @WSDLDocumentation("My portType description"), @WSDLDocumentation(value = "My top level description", placement = WSDLDocumentation.Placement.TOP),
		@WSDLDocumentation(value = "My binding description", placement = WSDLDocumentation.Placement.BINDING) })
public interface UserService {
	@WSDLDocumentation("获得所有数据")
	public String getUser();

	@WSDLDocumentation("<!-- \r  接口签名：public String getAll();\r  接口描述：获取所有数据  \r-->")
	public String getAll();
	
	@WSDLDocumentation("<!-- \r接口签名：public String getPage(Pager pager);\r接口描述：分页获取数据(仅支持数据库类型数据服务)\r接口参数：接口参数为分页对象Pager,对象属性说明如下： \r  1.start：int类型,表示第一个返回记录行的偏移量 \r  2.limit：int类型,指定返回记录行的最大数目 \r  3.sort：String类型,多排序条件的json串，格式为：[{'property':'列名1','direction':'DESC'},{'property':'列名2','direction':'ASC'}],'property'表示为数据库表的列名称,本接口可用属性名参考getByPropertyFilter接口说明 \r-->")
	public String getPage(Pager pager);
	
	@WSDLDocumentation("<!-- \r接口签名：public String getByPropertyFilter(List<PropertyFilter> listPropertyFilter);\r接口描述：通过多条件查询数据(仅支持数据库类型数据服务) \r接口参数：一个PropertyFilter对象代表一个查询条件,对象属性说明如下：\r  1.propertyName：String类型,过滤属性指数据库列名,本接口可用属性如下：\r _propertyList \r  2.value：Object类型,属性的值\r  3.matchType：MatchType枚举类型,表示属性比较类型,可用值有EQ(等于)、LIKE(同sql使用)、LT(小于)、LE(小于等于)、 GT(大于)、 GE(大于等于)、IN(同sql使用)-->")
	public String getByPropertyFilter(List<PropertyFilter> listPropertyFilter);
	
	@WSDLDocumentation("<!-- \r  接口签名：public String getPageByPropertyFilter(List<PropertyFilter> listPropertyFilter,Pager pager);\r  接口描述：通过多条件并分页查询数据(仅支持数据库类型数据服务)\r  接口参数：参考getPage以及getByPropertyFilter接口-->")
	public String getPageByPropertyFilter(List<PropertyFilter> listPropertyFilter,Pager pager);
}