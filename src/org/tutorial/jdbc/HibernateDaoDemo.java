package org.tutorial.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.jdbc.dao.HibernateDaoImpl;

public class HibernateDaoDemo {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springHibernateDao.xml");
		HibernateDaoImpl dao = applicationContext.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
		System.out.println(dao.getCircleCount());
	}

}
