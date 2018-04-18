package com.br.custom.insertdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Leonardo S. Rodrigues <leonardo.silva.rodrigues2@gmail.com>
 * @since 17/04/2018
 * @version 1.0
 */
public class ConnectionFactory {

    private static final String URL_CONNECTION = "URL_CONNECTION";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    public Connection getConnection(Properties properties) {
        try {
            return DriverManager.getConnection(properties.getProperty(URL_CONNECTION),
                    properties.getProperty(USERNAME),
                    properties.getProperty(PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
