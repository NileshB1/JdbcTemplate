package org.tutorial.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.tutorial.jdbc.model.Circle;

@Component
public class JdbcDaoImpl {

	public Circle getCircle(int circleId) {
		Circle circle = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		String driver = "org.apache.derby.jdbc.ClientDriver";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection("jdbc:derby://localhost:1527/myDB");

			ps = conn.prepareStatement("SELECT * from Circle WHERE id = ?");
			ps.setInt(1, circleId);
			rs = ps.executeQuery();

			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name").trim());
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException exp) {
			System.out.println("Exception occured: " + exp.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException sqlExp) {
				System.out.println("SQL exception: "+ sqlExp.getMessage());
			}
		}

		return circle;
	}
}
