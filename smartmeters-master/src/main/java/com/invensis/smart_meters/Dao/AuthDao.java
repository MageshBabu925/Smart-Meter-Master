package com.invensis.smart_meters.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean authenticate(String username, String password) {
		String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
		return count > 0;
	}

}