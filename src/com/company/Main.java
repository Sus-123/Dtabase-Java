package com.company;
import com.sun.jdi.connect.spi.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    static final String Driver = "com.mysql.jdbc";
    static final String url  = "jdbc:mysql://localhost/REGISTRATION";
    static final String user = "root";
    static final String pass = "Susu_1234";

    public static void main(String[] args) {

        java.sql.Connection con = null;
        Statement st = null;
        String query = "select * from Student";
       // String query2 = "select last from Student where id = 2 ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to selected database...");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to db successfully..");
            st = con.createStatement();

            //read
            ResultSet resultSet = st.executeQuery(query);
            String userData = "";
            while (resultSet.next()) {
                userData = resultSet.getInt(1) + " " + resultSet.getString(2) +" " + resultSet.getString(3);
                //System.out.println(userData);
            }

            //-----------------------------------------------------------
            // Insert values : for inserting we will use prepared statments.
            // each question mark represents the individual coloumn.
            String queryInsert = "insert into Student values (?,?,?,?)";
            ArrayList<Integer> ids = new ArrayList<>();
            ArrayList<String> first = new ArrayList<>();
            ArrayList<String> last = new ArrayList<>();
            ArrayList<Integer> age = new ArrayList<>();

            for (int i = 0; i <3 ; i++) {
                ids.add(i+10);
                first.add("first" + i);
                last.add("last" + i);
                age.add(20+i);
            }

            PreparedStatement statement = con.prepareStatement(queryInsert);

            for (int i = 0; i <3 ; i++) {
               // int idx = ids.get(i);
                statement.setInt(1, ids.get(i));
                statement.setString(2,first.get(i));
                statement.setString(3, last.get(i));
                statement.setInt(4,age.get(i));
                int rowsAffected = statement.executeUpdate();
                System.out.println(rowsAffected);
            }

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
