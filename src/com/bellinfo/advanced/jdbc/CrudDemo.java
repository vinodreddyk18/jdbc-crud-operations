package com.bellinfo.advanced.jdbc;
import java.sql.*;
import java.util.Properties;

public class CrudDemo {
    public static void main(String[] args) {

        String url ="jdbc:postgresql://127.0.0.1:5432/belljavasep";
        Properties prop = new Properties();
        prop.setProperty("user","postgres");
        prop.setProperty("password", "vinod143");
        prop.setProperty("currentSchema", "learning");
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url,prop);
            Statement stmt = con.createStatement();
            String query = "select * from cars";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
