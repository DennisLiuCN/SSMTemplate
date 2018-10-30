package org.bluesky.ssm.json;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JavaIdentifierTransformer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Json测试类
 * 
 * @author: liuyuefeng
 * @date: 2015年10月29日 下午4:39:29
 * @version: V1.0
 *
 */
public class JsonTest {
	private static final Logger logger = LoggerFactory.getLogger(JsonTest.class);

	/**
	 * JsonObject对象转换基本使用
	 * @return: void
	 * @throws:
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@Test
	public void testJsonObject() {
		/**
		 * 解析Map
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Tom");
		map.put("age", 33);
		JSONObject jsonObject = JSONObject.fromObject(map);
		logger.info(jsonObject.toString());
		
		/**
		 * 解析JavaBean
		 */
		Person person = new Person("A001", "Jack", 25, Person.SEX.MALE, 2000.08);
		jsonObject = jsonObject.fromObject(person);
		logger.info(jsonObject.toString());
		
		/**
		 * 解析嵌套的对象
		 */
		map.put("person", person);
		jsonObject = jsonObject.fromObject(map);
		logger.info(jsonObject.toString());
		
		/**
		 * 将 Json形式的字符串转换为Map
		 */
		String str = "{\"name\":\"Tom\",\"age\":90}";
		JSONObject jsonObject1 = JSONObject.fromObject(str);
		Map<String, Object> map1 = (Map<String, Object>) JSONObject.toBean(jsonObject1, Map.class);
		System.out.println(map1);

		/**
		 * 将 Json形式的字符串转换为JavaBean
		 * 在将Json形式的字符串转换为JavaBean的时候需要注意JavaBean中必须有无参构造函数，否则会报如下找不到初始化方法的错误
		 */
		/*str = "{\"id\":\"A001\",\"name\":\"Jack\",\"sex\":\"FEMALE\",\"money\":2000.08}";
		jsonObject1 = JSONObject.fromObject(str);
		System.out.println(jsonObject1);
		Person person1 = (Person) JSONObject.toBean(jsonObject1, Person.class);
		System.out.println(person1.toString());*/
		
		str = "{\"billInfo\":[{\"billType\":\"1\",\"billNo\":\"9707\",\"billDate\":\"201405\",\"customerId\":\"5001\",\"customerName\":\"传立（北京）\",\"contract\":\"20160105-001\",\"billAmount\":3000000.89,\"currencyName\":\"人民币\"},{\"billType\":\"1\",\"billNo\":\"9708\",\"billDate\":\"201605\",\"customerId\":\"5001\",\"customerName\":\"传立（北京）\",\"contract\":\"20160105-001\",\"billAmount\":2000000.01,\"currencyName\":\"人民币\"},			 ]}";
		JSONObject jsonObject2 = JSONObject.fromObject(str);
		System.out.println("jsonObject2"+jsonObject2.get(""));
	}
	
	/**
	 * JsonArray集合转换基本使用
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testJsonArray() {
		JSONArray jsonArray = JSONArray.fromObject("[89,90,99]");
		Object array = JSONArray.toArray(jsonArray);
		System.out.println(Arrays.asList((Object[]) array));
	}
	
	/**
	 * JavaBean中包含Array时转换成Json
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testJsonArrayAndObject() {
		NewPerson person = new NewPerson("A001", "Jack", 25, 2000.08);
		JSONObject jsonObject = JSONObject.fromObject(person);
		logger.info(jsonObject.toString());
	}
	
	/**
	 * 排除需要序列化成json的属性
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testExcludeProperites() {
		String str = "{\"id\":\"A001\",\"name\":\"Jack\",\"sex\":\"FEMALE\",\"money\":2000.08}";
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "name", "sex" });
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(str, jsonConfig);
		
		logger.info("id:{};", jsonObject.getString("id"));
		logger.info("money:{};", jsonObject.getDouble("money"));
		logger.info("has(name):{};", jsonObject.has("name"));
		logger.info("has(sex):{}.", jsonObject.has("sex"));
	}
	
	/**
	 * 防止自包含
	 * 如果Person类中含有属性private Person me = this;
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testCycleObject() {
		Person person = new Person("A002", "Tom", 25, Person.SEX.FEMALE, 3400.10);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		JSONObject jsonObject = JSONObject.fromObject(person, jsonConfig);
		logger.info("jsonObject:{}", jsonObject);
	}
	
	/**
	 * 日期和性别输出格式转换
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testDateAndSexProcessor() {
		JsonConfig cfg = new JsonConfig();
		
		JsonValueProcessor dateProcessor = new JsonValueProcessor() {
			@Override
			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				//return value;
				return processObjectValue(null, value, jsonConfig);
			}

			@Override
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				/*DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
				return df.format((Date) value);*/
				Date valueDate = (Date) value;
				String valueString = "\\/Date(" + valueDate.getTime() + "+0800)\\/";
				return valueString;
			}
		};
		cfg.registerJsonValueProcessor(Date.class, dateProcessor);
		
		JsonValueProcessor sexProcessor = new JsonValueProcessor() {
			@Override
			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				//return value;
				return processObjectValue(null, value, jsonConfig);
			}

			@Override
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				if (Person.SEX.MALE.equals(value)) {
					return "男性";
				} else if (Person.SEX.FEMALE.equals(value)) {
					return "女性";
				} else
					return "未知";
			}
		};
		cfg.registerJsonValueProcessor(Person.class, "sex", sexProcessor);
		
		Person person = new Person("B102", "郑永生", 28, Person.SEX.FEMALE, 50000.1);
		logger.info("Person:{}", JSONObject.fromObject(person, cfg));
		JSONObject jObject = JSONObject.fromObject(person, cfg);
		System.out.println(jObject.get("birthday"));
	}
	
	/**
	 * bean与json结构不一致转换
	 * 有时候bean和json的属性（key）并不是一一对应的，比如bean中的name用一个string属性表示，而json中可能需要用firstName和lastName表示，这时可以用JsonBeanProcessor来实现
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testBeanProcessor(){
		JsonBeanProcessor processor = new JsonBeanProcessor() {
			@Override
			public JSONObject processBean(Object bean, JsonConfig jsonConfig) {
				JSONObject json = new JSONObject();
				Person person = (Person) bean;
				String[] names = person.getName().split("\\.");
				json.put("id", person.getId());
				json.put("firstName", names[0]);
				json.put("lastName", names[1]);
				json.put("age", person.getAge());
				json.put("sex", person.getSex());
				json.put("money", person.getMoney());
				return json;

			}
		};
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonBeanProcessor(Person.class, processor);
		Person person = new Person("B102", "乔治.华盛顿", 28, Person.SEX.FEMALE, 50000.1);
		System.out.println(JSONObject.fromObject(person, cfg));
	}
	
	/**
	 * 将 Json形式的字符串转换为JavaBean
	 * 如果Json形式的字符串中属性起始字母为大写需要转换为小写与JavaBean中属性一致
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testJavaIdentifierTransformer(){
		JavaIdentifierTransformer processor = new JavaIdentifierTransformer() {
			@Override
			public String transformToJavaIdentifier(String str) {
				char[] chars = str.toCharArray();
				chars[0] = Character.toLowerCase(chars[0]);
				return new String(chars);
			}
		};
		JsonConfig cfg = new JsonConfig();
		cfg.setJavaIdentifierTransformer(processor);
		String str = "{\"Id\":\"A001\",\"Name\":\"Jack\",\"Money\":2000.08,\"Company\":{\"Name\":\"爱奇艺\",\"Address\":\"中关村\"}}";
		JSONObject jsonObject = JSONObject.fromObject(str);
		cfg.setRootClass(Person.class);
		Person person = (Person) JSONObject.toBean(jsonObject, cfg);
		System.out.println(person.toString());
	}
}
/**
 * Json-lib使用说明
 * 
 * 默认解析器解析过程如下：
 * 1.创建一个空的JSONObject对象
 * 2.获取bean中可序列化的属性(按getter方法获取)；
 * 3.过滤掉由transient关键字修饰的属性(属性声明或getter声明上都不能有transient关键字，可以在jsonConfig中关闭这这个功能)；
 * 4.如果在JsonConfig中给当前bean类型注册 了jsonPropertyFilter，则执行这个过滤器,过滤掉不需要序列化的属性(jsonPropertyFilter稍后讲解)；
 * 5.如果注册了JsonValueProcessor则执行执行这个Processor，对属性值进行处理(一般序列化日期时在这里进行)；
 * 6.如果注册了propertyNameProcessor则执行这个Processor，对属性名(对应json的key)进行处理(java属性和json key名称不一样可以在这里处理)；
 * 7.将key，value写入JSONObject对象。
 * 
 * 解析器：
 * 如何将java对象用json标示，最终是由解析器来决定的。
 * json-lib提供以下几种解析器接口：
 * DefaultValueProcessor：为java类型指定默认值，我们一般不需要实现这个接口，json-lib提供的默认实现基本上可以满足我们的需要。
 * JsonBeanProcessor：为java对象指定一个解析器，稍后举例说明。
 * JsonValueProcessor：为bean的值指定一个解析器，可以将bean中的值输出到json后使用另外的值表示，稍后举例说明
 * PropertyNameProcessor：为bean的属性名指定一个解析器，可以将bean中属性名称输出到json后变成另一个名称。
 * 一个JsonConfig对象可以注册多个解析器，既可以为按bean的类型注册解析器，也可以给特定类型的某一个属性指定解析器。
 * 
 * 过滤器
 * 将java bean序列化为json时，有些属性不需要写到json中，这时需要将这些属性过滤掉。
 * json-lib提供了三种方式来完成这项工作：
 * 1.在java 类对属性或属性的getter方法用关键字transient修饰。如： private transient double salary；需要在JsonConfig对象中开启这个功能， jsonConfig.setIgnoreTransientFields(true);
 * 2.在JsonConfig中显示声明需要忽略的属性（推荐使用这种方式）， jsonConfig.registerPropertyExclusion(Employee.class, "salary")， 该接口接收两个参数，一个是需要过滤的属性所属的java类， 一个是属性名称。
 * 3.使用PropertyFilter，这个方式最强大而且灵活，可以在序列化过程中根据bean的类型、名称甚至值进行过滤。
 * 使用PropertyFilter过滤，需要实现PropertyFilter接口：
 * public interface PropertyFilter {
 *    /*
 *     * @param source属性所属的java bean对象
 *     * @param name 属性名称
 *     * @param value 属性值
 *     * @return 如果不希望当前属性写入json，则返回true，否则返回false
 *     *\/
 *    boolean apply(Object source, String name, Object value);
 * }
 * 将过滤器注册到JavaConfig：javaConfig.setJsonPropertyFilter(propertyFilter)；
 * 一般来说，一个JavaCofig只能注册一个过滤器，有时候我们可能需要多个过滤器联合完成属性的过滤，这时可以考虑json-lib提供的几个PropertyFilter的几个实现类：
 * AndPropertyFilter：组合两个过滤器实例，当同时满足两个过滤器过滤条件时，属性才会被过滤；
 * OrPropertyFilter：组合两个过滤器实例，当属性满足其中一个过滤器过滤条件时，属性就会被过滤；
 * NotPropertyFilter：组合一个过滤器A，当属性满足过滤器A时，不对属性进行过滤，否则对属性进行过滤；
 * CompositePropertyFilter：组合多个过滤器，当属性满足其中一个过滤器过滤条件时，属性就会被过滤；
 * FalsePropertyFilter：所有属性都不会被过滤；
 * TruePropertyFilter：所有属性都会被过滤（不知道这个有什么用，^_^）；
 * MappingPropertyFilter：这个是抽象类，可以设置一组键值对存储的过滤器，每读取到一个bean属性时都会在这一组过滤器中寻找一个过滤器进行过滤，具体使用哪一个过滤器由方法keyMatches来决定。
 * public abstract class MappingPropertyFilter implements PropertyFilter {
 *    private Map filters = new HashMap();
 *      /**
 *       *@param key 过滤器对应的key值
 *       *@param source bean属性所属的java类
 *       *@param name bean属性名称
 *       *@param value bean属性的值
 *       *@return 返回true时，将使用key指向的过滤器过滤bean属性
 *       *\/
 *      protected abstract boolean keyMatches(Object key, Object source, String name, Object value);
 *          ....
 *      }
 * }
 * 
 */
