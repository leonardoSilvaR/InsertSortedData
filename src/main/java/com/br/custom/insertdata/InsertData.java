package com.br.custom.insertdata;

import com.br.custom.insertdata.data.Banks;
import com.br.custom.insertdata.data.Countries;
import com.br.custom.insertdata.data.Customers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo S. Rodrigues <leonardo.silva.rodrigues2@gmail.com>
 * @since 17/04/2018
 * @version 1.0
 */
public class InsertData {

    private static final String PROPERTIES_NAME = "properties.properties";
    private static final String INSERT_QUERY = "INSERT_QUERY";
    private static final String COUNT = "COUNT_TOTAL_ROWS";

    public static Properties getProperties() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(PROPERTIES_NAME)) {
            properties.load(resourceStream);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, "Cannot read properties", ex);
        }
        return properties;
    }

    public static void main(String[] args) throws SQLException, IOException {
        SortData sort = new SortData();
        Properties properties = getProperties();
        try (Connection conn = new ConnectionFactory().getConnection(properties)) {
            try (PreparedStatement pstmt = conn.prepareStatement(properties.getProperty(INSERT_QUERY))) {
                for (int i = 0; i < 20000000; i++) {
                    pstmt.setInt(1, sort.getData(Banks.BANK_CODE));
                    pstmt.setString(2, sort.getData(Banks.BANK_NAME));
                    pstmt.setString(3, sort.getData(Countries.NAMES));
                    pstmt.setString(4, sort.getData(Customers.CARD_NUMBERS));
                    pstmt.setString(5, sort.getData(Customers.DOCUMENTS));
                    pstmt.setString(6, sort.getData(Customers.NAMES));
                    pstmt.execute();
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(properties.getProperty(COUNT))) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int numberOfRows = rs.getInt(1);
                    System.out.println("Number of rows in table: " + numberOfRows);
                } else {
                    System.out.println("error: could not get the record counts");
                }
            }
        }
    }
}
