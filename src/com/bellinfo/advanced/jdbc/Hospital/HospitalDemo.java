package com.bellinfo.advanced.jdbc.Hospital;


import java.sql.SQLException;
import java.util.Scanner;

public class HospitalDemo {

    enum OPERATIONS{ INSERT, SEARCWITHHNAME, SEARCHWITHRATE, FETCH};

    public static void main(String[] args) throws SQLException {


        HospitalRepository hr = new HospitalRepository();
        hr.createHospital();

        Scanner scan = new Scanner(System.in);

        System.out.println("Hey what do you want to do");
        String oper = scan.next();
        if(oper.equalsIgnoreCase(OPERATIONS.INSERT.name())){
            System.out.println("How many records");
            int count = scan.nextInt();
            hospital h = null;
            for(int i = 0;i<count;i++){
                h = new hospital();
                System.out.println("Hid");
                h.setHid(scan.nextInt());
                System.out.println("Hospital name");
                h.setHname(scan.next());
                System.out.println("location");
                h.setHLoc(scan.next());
                System.out.println("Rating");
                h.setRating(scan.nextFloat());
                hr.insertHospitalRecord(h);

            }
        }else if(oper.equalsIgnoreCase(OPERATIONS.SEARCWITHHNAME.name())){

            System.out.println("which hospital you want to look for");
            String hosp = scan.next();
            hr.searchHospitalWithName(hosp);

        }else if(oper.equalsIgnoreCase(OPERATIONS.SEARCHWITHRATE.name())){
            System.out.println("Which rating you want to look for");
            Double rate = scan.nextDouble();
            hr.searchWithRating(rate);

        }else if(oper.equalsIgnoreCase(OPERATIONS.FETCH.name())){
            hr.fetchRecords();
        }

    }
}
