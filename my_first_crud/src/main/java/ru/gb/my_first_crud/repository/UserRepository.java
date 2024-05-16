package ru.gb.my_first_crud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.my_first_crud.model.User;
import ru.gb.my_first_crud.props.SqlProperties;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;
    private final SqlProperties sqlProperties;

    @Autowired
    public UserRepository(JdbcTemplate jdbc, SqlProperties sqlProperties) {
        this.jdbc = jdbc;
        this.sqlProperties = sqlProperties;
    }

    public List<User> findAll() {
        String sql = String.format(sqlProperties.getSelectAllFrom(), sqlProperties.getTable());

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("first_name"));
            rowObject.setLastName(r.getString("last_name"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = String.format(sqlProperties.getInsertInto(), sqlProperties.getTable(), sqlProperties.getFirstName(), sqlProperties.getLastName());
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id) {
        String sql = String.format(sqlProperties.getDeleteFrom(), sqlProperties.getTable(), sqlProperties.getId());
        jdbc.update(sql, id);
    }
    public void updateUser(User user) {
        String sql = String.format(sqlProperties.getUpdate(), sqlProperties.getTable(), sqlProperties.getFirstName(), sqlProperties.getLastName(), sqlProperties.getId());
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    public User getOne(int id) {
        String sql = String.format(sqlProperties.getSelectAllFromWhere(), sqlProperties.getTable(), sqlProperties.getId());

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("first_name"));
            rowObject.setLastName(r.getString("last_name"));
            return rowObject;
        };

        return jdbc.queryForObject(sql, userRowMapper, id);
    }
}