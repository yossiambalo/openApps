package com.odysii;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlManager {
    public static boolean executeQuery(String query){
        boolean res = true;
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("roi");
        dataSource.setPassword("roi1");
        dataSource.setServerName("openapps.tveez.local");
        Connection conn = null;
        Statement stmt = null;
       try {
           conn = dataSource.getConnection();
           stmt = conn.createStatement();
           stmt.executeUpdate(query);
       }catch (SQLException e){
           System.out.println(e.getMessage());
           res = false;
       }finally {
           try {
               stmt.close();
               conn.close();
           }catch (SQLException e){
               System.out.println(e.getMessage());
           }
       }
       return res;
    }
}
