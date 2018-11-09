package com.bellinfo.advanced.jdbc.Hospital;


import java.sql.*;
import java.util.Properties;

public class HospitalRepository {

    private static final String CREATE_SQL = "CREATE TABLE Hospital ( Hid int, Hname varchar, Hlocation varchar, Rating float )";
    private static final String INSERT_SQL = "INSERT INTO Hospital VALUES(?,?,?, ?)";
    private static final String SELECT_SQL = "select * from Hospital";
    //private static final String UPDATE_SQL = "Update Hospital set Hlocation=? where Hname=?";
    private static final String SEARCH_SQL = "select * from hospital where hname=?";
    private static final String SEARCH_RATING = "select * from hospital where Rating = ?";
    private static final String VALIDATE_SQL= "select exists (select 1 from pg_tables where schemaname='public' and tablename='hospital')";

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/belljavasep";
    Connection con = null;

    void getConnection(){

        try{
            Class.forName("org.postgresql.Driver");
            Properties prop = new Properties();
            prop.setProperty("user","postgres");
            prop.setProperty("password", "Vinod@143");
            con = DriverManager.getConnection(URL,prop);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void createHospital() throws SQLException{
        getConnection();
        PreparedStatement psValidate = con.prepareStatement(VALIDATE_SQL);
        ResultSet rs = psValidate.executeQuery();
        boolean isTableExist = false;
        while(rs.next()){
            isTableExist = rs.getBoolean(1);
        }
        if(isTableExist){
            System.out.println("Hey ..Table already created in your schema, so skipping it");
        }else{
            PreparedStatement ps = con.prepareStatement(CREATE_SQL);
            boolean isCreated = ps.execute();
            System.out.println("Hey Table has been created");
        }
    }

    void insertHospitalRecord(hospital h){
        getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(INSERT_SQL);
            ps.setInt(1,h.getHid());
            ps.setString(2,h.getHname());
            ps.setString(3,h.getHLoc());
            ps.setDouble(4,h.getRating());

            ps.executeUpdate();
            System.out.println("Record created");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    void searchHospitalWithName(String hosp) throws SQLException{
        getConnection();
        PreparedStatement ps = con.prepareStatement(SEARCH_SQL);
        ps.setString(1,hosp);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("Hid") + " " + rs.getString("Hname")+ " " + rs.getString("HLocation") + " " + rs.getFloat("Rating"));
        }


    }


    void searchWithRating(double rate) throws SQLException{
        getConnection();
        PreparedStatement ps = con.prepareStatement(SEARCH_RATING);
        ps.setDouble(1,rate);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("Hid") + " " + rs.getString("Hname")+ " " + rs.getString("HLocation") + " " + rs.getFloat("Rating"));
        }
    }

    void fetchRecords() throws SQLException{
        getConnection();
        PreparedStatement ps = con.prepareStatement(SELECT_SQL);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("Hid") + " " + rs.getString("Hname")+ " " + rs.getString("HLocation") + " " + rs.getFloat("Rating"));
        }
    }

}
