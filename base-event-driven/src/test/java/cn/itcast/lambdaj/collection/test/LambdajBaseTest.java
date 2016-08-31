package cn.itcast.lambdaj.collection.test;

import java.util.ArrayList;
import ch.lambdaj.function.closure.Closure; 
import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.itcast.lambdaj.collection.Person;

public class LambdajBaseTest {
	private List<Person> persons = new ArrayList<Person>();

	@Before
	public void init() {
		Person p = new Person();
		p.setId(1);
		p.setName("张三");
		p.setAge(28);
		persons.add(p);
		
		p = new Person();
		p.setId(2);
		p.setName("李四");
		p.setAge(35);
		
		persons.add(p);
	}
	
	/*
	 *  joinFrom（连接字段）
	 */
	@Test
	public void testJoinFrom() {
		String names = joinFrom(persons).getName() ;
		System.out.println(names);
		names = joinFrom(persons, "==").getName() ;
		System.out.println(names);
	}
	
	/*
	 * select(条件选择)
	 * selectMax,selectMin(最大/最小 对象)
	 */
	@Test
	public void testSelect() {
		// 筛选出年龄大于33岁的人
		List<Person> ageGreaterThan33 = select(persons,having(on(Person.class).getAge(),Matchers.greaterThan(33)));
		System.out.println(JSONArray.toJSONString(ageGreaterThan33));
		
		Person personWithMaxAge = selectMax(persons, on(Person.class).getAge());// 得到年龄最大的人
		System.out.println(JSONObject.toJSONString(personWithMaxAge));
	}
	
	/*
	 * max,min(最大/最小 对象属性值)
	 * maxFrom,minFrom(和max,min功能一样)
	 * sum,sunFrom(求和)
	 */
	@Test
	public void testMinMax() {
		int maxAge = max(persons, on(Person.class).getAge()) ;
		System.out.println(maxAge);
		
		maxAge = maxFrom(persons).getAge() ;
		System.out.println(maxAge);
		
		int minAge = minFrom(persons).getAge() ;
		System.out.println(minAge);
		
		System.out.println(sumFrom(persons).getAge());
		System.out.println(sum(persons, on(Person.class).getAge()));
	}
	
	// sort(排序) extract(抽取字段属性组成集合)
	@Test
	public void testSortExtra() {
		System.out.println(JSONArray.toJSONString(persons));
		List<Person> sortsP = sort(persons, on(Person.class).getAge()) ;
		System.out.println(JSONArray.toJSONString(sortsP));
	
		List<Integer> ageList = extract(persons, on(Person.class).getAge());
		System.out.println(JSONArray.toJSONString(ageList));
	}
	
	// 以某字字段属性为关键值分组
	@Test
	public void indexP() {
		Map<String,Person> mapByName = index(persons, on(Person.class).getName()) ;
		System.out.println(JSONObject.toJSONString(mapByName));
	}
}
