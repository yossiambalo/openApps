package com.odysii;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
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

    public static String executeQuery(String query, String userName, String password,String server){
        String res = "";
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            ResultSet resultSet =  stmt.executeQuery(query);
            while (resultSet.next()){
                res = resultSet.getString(4);
                break;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return res;
    }
}
