package ru.gb.my_first_crud.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sql")
@Data
public class SqlProperties {

    private String selectAllFrom;
    private String selectAllFromWhere;
    private String insertInto;
    private String deleteFrom;
    private String update;

    private String table;
    private String id;
    private String firstName;
    private String lastName;


}