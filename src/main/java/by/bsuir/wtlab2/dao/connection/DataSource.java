package by.bsuir.wtlab2.dao.connection;

import by.bsuir.wtlab2.exception.ConnectionException;

import java.sql.Connection;

public interface DataSource {
    Connection getConnection() throws ConnectionException;
}
