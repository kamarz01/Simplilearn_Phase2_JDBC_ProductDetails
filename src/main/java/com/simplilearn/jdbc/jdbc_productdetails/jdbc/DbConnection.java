package com.simplilearn.jdbc.jdbc_productdetails.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private Connection dbConnection;

    public DbConnection(String url,String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection(){
        return this.dbConnection;
    }

    public void closeConnection() throws SQLException {
        if (this.dbConnection != null)
            this.dbConnection.close();
    }

}
