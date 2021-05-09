package model.dao.mapper;

import model.entity.User;
import model.entity.type.Role;
import model.entity.type.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User>{
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getInt("password"));
        user.setRole(Enum.valueOf(Role.class , rs.getString("role")));
        user.setStatus(Enum.valueOf(Status.class,rs.getString("status")));
        return user;
    }
}
