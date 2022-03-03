package com.company;
import com.sun.jdi.connect.spi.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        PlayerService playerService = new PlayerService();
        playerService.makeConnection();

        String name = "T3P3";
        Player player = new Player();
        player.name = name;
        player.run=100;
        player.balls= 100;


        System.out.println("dcd");
       // playerService.addPlayer(player);
       //int id =  playerService.addInning("suresh6", 101);

        int  run  = playerService.getPlayer("Pak");
       // System.out.println(p1.name + " " + p1.run + " " + p1.balls);
         System.out.println(run);
    }



}
