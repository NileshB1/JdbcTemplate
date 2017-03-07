package org.tutorial.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.jdbc.dao.JdbcTemplateDaoImpl;
import org.tutorial.jdbc.model.Circle;

public class JdbcTemplateDemo {

		public static void main(String[] args) {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springJdbcTemplate.xml");
			JdbcTemplateDaoImpl dao = applicationContext.getBean("jdbcTemplateDaoImpl", JdbcTemplateDaoImpl.class);
			
			System.out.println(dao.getCircleCount());
			System.out.println(dao.getCircleName(1));
			System.out.println(dao.getCircleForId(1));
			
			/*dao.insertCircle(new Circle(2, "Second circle"));
			System.out.println(dao.getAllCircle());
			dao.createTriangleTable();*/
			
//			dao.namedParameterInsertCircle(new Circle(4, "Named Param Circle1"));
			System.out.println(dao.getAllCircle());
		}
}
