package org.tutorial.jdbc.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SpringDaoSupportImpl extends JdbcDaoSupport {

	public int getCircleCount() {
		int count = 0;
		String cntSql = "SELECT count(*) from circle";
		count = getJdbcTemplate().queryForObject(cntSql, Integer.class);
		return count;
	}
}
