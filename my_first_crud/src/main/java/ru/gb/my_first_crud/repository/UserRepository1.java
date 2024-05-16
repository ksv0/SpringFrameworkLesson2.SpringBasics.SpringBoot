package ru.gb.my_first_crud.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.my_first_crud.model.User;
import ru.gb.my_first_crud.service.StringService;


import java.util.List;

@Repository
@AllArgsConstructor
@Deprecated
public class UserRepository1 {
    private final JdbcTemplate jdbc;
    public List<User> findAll() {
        String sql = String.format(
                StringService.get(StringService.SELECT_ALL_FROM),
                StringService.get(StringService.TABLE));

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt(StringService.get(StringService.ID)));
            rowObject.setFirstName(r.getString(StringService.get(StringService.FIRST_NAME)));
            rowObject.setLastName(r.getString(StringService.get(StringService.LAST_NAME)));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = String.format(
                StringService.get(StringService.INSERT_INTO),
                StringService.get(StringService.TABLE),
                StringService.get(StringService.FIRST_NAME),
                StringService.get(StringService.LAST_NAME));
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id) {
        String sql = String.format(
                StringService.get(StringService.DELETE_FROM),
                StringService.get(StringService.TABLE),
                StringService.get(StringService.ID));
        jdbc.update(sql, id);
    }

    public void updateUser(User user) {
        String sql = String.format(
                StringService.get(StringService.UPDATE),
                StringService.get(StringService.TABLE),
                StringService.get(StringService.FIRST_NAME),
                StringService.get(StringService.LAST_NAME),
                StringService.get(StringService.ID));
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    public User getOne(int id) {
        String sql =String.format(
                StringService.get(StringService.SELECT_ALL_FROM_WHERE),
                StringService.get(StringService.TABLE),
                StringService.get(StringService.ID));

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt(StringService.get(StringService.ID)));
            rowObject.setFirstName(r.getString(StringService.get(StringService.FIRST_NAME)));
            rowObject.setLastName(r.getString(StringService.get(StringService.LAST_NAME)));
            return rowObject;
        };
        return jdbc.queryForObject(sql, userRowMapper, id);
    }

}
