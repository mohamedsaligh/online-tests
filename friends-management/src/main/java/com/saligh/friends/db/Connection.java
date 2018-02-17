package com.saligh.friends.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by saligh on 17/2/18.
 */
public class Connection {

    public static java.sql.Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        System.out.println("DATABASE_URL: " + dbUri.getHost() + "|" + dbUri.getPort());
        System.out.println("DATABASE_URL from System env: " + System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        System.out.println("username: " + username);
        String password = dbUri.getUserInfo().split(":")[1];
        System.out.println("password: " + password);
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        System.out.println("postgre dbUrl: " + dbUrl);

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void closeResources(ResultSet rs, Statement stmt, java.sql.Connection con) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch(Exception ex) {
            System.err.println("Exception in closing ResultSet: " + ex);
        }

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch(Exception ex) {
            System.err.println("Exception in closing Statement: " + ex);
        }

        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch(Exception ex) {
            System.err.println("Exception in closing connection: " + ex);
        }
    }
}
