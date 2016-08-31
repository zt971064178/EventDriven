package cn.itcast.lambdaj.collection.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void testJoinFrom() {
		
	}
	
}
