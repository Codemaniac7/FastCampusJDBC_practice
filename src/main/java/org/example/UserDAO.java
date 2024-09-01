package org.example;

import java.sql.*;

public class UserDAO {

    public void create(User user) throws SQLException {
        JDBCTemplate jdbcTemplate = new JDBCTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(user, sql, new PreparedStatementSetter() {
            @Override
            public void setter(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserid());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        });
    }


    public User findByUserId(String userId) throws SQLException {
        JDBCTemplate jdbcTemplate = new JDBCTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid = ?";
        return (User)jdbcTemplate.executeQuery(sql, pstmt -> pstmt.setString(1, userId), resultSet -> new User(
                resultSet.getString("userId"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("email")
        ));
    }
}
