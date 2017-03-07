package org.tutorial.jdbc;

import org.tutorial.jdbc.dao.JdbcDaoImpl;
import org.tutorial.jdbc.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		Circle circle = new JdbcDaoImpl().getCircle(1);
		System.out.println(circle);
	}

}
