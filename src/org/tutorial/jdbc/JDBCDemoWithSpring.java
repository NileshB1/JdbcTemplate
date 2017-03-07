package org.tutorial.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.jdbc.dao.JdbcDaoImplNew;

public class JDBCDemoWithSpring {

		public static void main(String[] args) {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
			JdbcDaoImplNew dao = applicationContext.getBean("jdbcDaoImplNew", JdbcDaoImplNew.class);
			
//			Circle circle = new JdbcDaoImpl().getCircle(1);
			System.out.println(dao.getCircle(1));
		}
}
