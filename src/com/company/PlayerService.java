package com.company;
import java.sql.*;

public class PlayerService {
    private String user;
    private String pass;
    private String url ;

    private Connection con;
    //statment can be different depending , like normal or prepared



    public void  makeConnection () {
        this.user = "root";
        this.pass = "Susu_1234";
        this.url = "jdbc:mysql://localhost/Cricket";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/Cricket","root",pass);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Player getPlayer(String name) throws ClassNotFoundException, SQLException {

        Player p = new Player();
        String query = "SELECT * FROM Cricket.Team1";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        rs.next();
        p.name = rs.getString(1);
        p.run = rs.getInt(2);
        p.balls = rs.getInt(3);
        return p;
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

}
