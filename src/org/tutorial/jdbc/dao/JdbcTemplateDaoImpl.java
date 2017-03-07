package org.tutorial.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.tutorial.jdbc.model.Circle;

@Component
public class JdbcTemplateDaoImpl {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Circle getCircle(int circleId) {
		Circle circle = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();

			ps = conn.prepareStatement("SELECT * from Circle WHERE id = ?");
			ps.setInt(1, circleId);
			rs = ps.executeQuery();

			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name").trim());
			}
		} catch (SQLException exp) {
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
	
	public int getCircleCount() {
		int count = 0;
		String cntSql = "SELECT count(*) from circle";
		count = jdbcTemplate.queryForObject(cntSql, Integer.class);
		return count;
	}
	
	public String getCircleName(int circleId) {
		String sql = "SELECT name FROM circle where ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, String.class);
	}
	
	public Circle getCircleForId(int circleId) {
		String sql = "SELECT * FROM circle where ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{circleId}, new CircleMapper());
	}
	
	public List<Circle> getAllCircle() {
		List<Circle> circleList = null;
		String sql = "SELECT * FROM circle";
		circleList = jdbcTemplate.query(sql, new CircleMapper());
		return circleList;
	}
	
	public void insertCircle(Circle circle) {
		String sql = "INSERT INTO circle (ID, NAME) values (?, ?)";
		jdbcTemplate.update(sql, new Object[]{circle.getId(), circle.getName()});
	}
	
	public void createTriangleTable() {
		String sql = "CREATE TABLE TRIANGLE(ID INTEGER, NAME VARCHAR(50))";
		jdbcTemplate.execute(sql);
	}
	
	public void namedParameterInsertCircle(Circle circle) {
		String sql = "INSERT INTO circle (ID, NAME) values (:id, :name)";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", circle.getId()).
				addValue("name", circle.getName());
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static final class CircleMapper implements RowMapper<Circle> {

		@Override
		public Circle mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("id"));
			circle.setName(resultSet.getString("name"));
			return circle;
		}
		
	}
}
