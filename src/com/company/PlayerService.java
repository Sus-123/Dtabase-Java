package com.company;
import java.sql.*;

public class PlayerService {
    private String user;
    private String pass;
    private String url ;

    private Connection con;
    //statment can be different depending , like normal or prepared

//    com.mysql.cj.jdbc.Driver

    public void  makeConnection () {
        this.user = "root";
        this.pass = "Susu_1234";
        this.url = "jdbc:mysql://localhost/CrecketMatch";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/CrecketMatch","root",pass);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int getTeamIdFromTeamName(String team) throws ClassNotFoundException, SQLException {

        String teamName = "'" + team + "'";
        String query = " select * FROM CrecketMatch.Team where Team_Name = " + teamName ;

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(query);

        if(rs.next()) {
            int id = rs.getInt(1);
            return  id;
        }

        return -1;

    }

    public int getPlayer (String teamName) throws SQLException, ClassNotFoundException {

        if(getTeamIdFromTeamName(teamName) != -1) {
            return getTeamIdFromTeamName(teamName);
        }

        con.setAutoCommit(false);

        String query =  "INSERT INTO `CrecketMatch`.`Team` (`Team_Name`) VALUES (?)";

        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,teamName);
        int teamId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                teamId = rs.getInt(1);
            }
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            throw e;
        }

        return teamId;

    }




    public void addPlayer(Player player) throws SQLException {
         System.out.println(player.name + " " + player.run + " " + player.balls);

         String query = "insert into Team1 values (?,?,?)";
         PreparedStatement preparedStatement = con.prepareStatement(query);

         preparedStatement.setString(1,player.name);
         preparedStatement.setInt(2,player.run);
         preparedStatement.setInt(3,player.balls);
         preparedStatement.executeUpdate();

    }

    public int addInning (String name, int score) throws SQLException {

        //INSERT INTO `Cricket`.`Inning` (`name`, `Score`, `Balls`, `overs`) VALUES ('Shayam', '50', '35', '2');
        con.setAutoCommit(false);
        String query = "INSERT INTO `Cricket`.`demo` (`run`, `name`) VALUES (?, ?)";

//        PreparedStatement preparedStatement = con.prepareStatement(query);
//
//        preparedStatement.setInt(1,score);
//        preparedStatement.setString(2,name);
//       // preparedStatement.setInt(3,balls);
//       // preparedStatement.setInt(4,overs);
//
//        preparedStatement.executeUpdate();


        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,score);
        preparedStatement.setString(2,name);

        int id  = 0;

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        }

        return id;



    }

}
