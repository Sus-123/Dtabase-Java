package com.company;
import com.sun.jdi.connect.spi.Connection;
import java.sql.Statement;
import java.sql.*;

public class Main {
    static final String Driver = "com.mysql.jdbc";
    static final String url  = "jdbc:mysql://localhost/REGISTRATION";
    static final String user = "root";
    static final String pass = "Susu_1234";

    public static void main(String[] args) {
        System.out.println("Lets learn jdbc");
        System.out.println("Its a fun");
        java.sql.Connection con = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to selected database...");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to db successfully..");
            System.out.println("Creating table in selected database..");
            st = con.createStatement();

            String sql = "CREATE TABLE  REGISTRATION" +
                    "(id INTEGER not null," +
                    "first VARCHAR(255)," +
                    "last VARCHAR(255)," +
                    "age INTEGER, " +
                    "primary key(id))";
            st.executeUpdate(sql);
            System.out.println("table created in given database");


        } catch (SQLException e){
            e.printStackTrace();

        } catch ( Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            if( con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Good bye!");

    }

}
