package org.tutorial.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.jdbc.dao.SpringDaoSupportImpl;

public class SpringDaoSupportDemo {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringDaoSupport.xml");
		SpringDaoSupportImpl dao = applicationContext.getBean("springDaoSupportImpl", SpringDaoSupportImpl.class);
		System.out.println(dao.getCircleCount());
	}

}
