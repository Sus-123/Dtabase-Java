package com.company;
import com.sun.jdi.connect.spi.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        PlayerService playerService = new PlayerService();
        playerService.makeConnection();

        String name = "Virat";
        Player player = new Player();
        player.name = name;
        player.run=100;
        player.balls= 50;

        playerService.addPlayer(player);

       // Player p1 = playerService.getPlayer(name);
       // System.out.println(p1.name + " " + p1.run + " " + p1.balls);
    }



}
