/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Password.Password;
import User.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author adelali
 */
public class DataBaseManager {

    public static String DBPort =""+ 8889;

    public static void CreatePasswords(Password p, User u) {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:"+DBPort+"/SPM", "root", "root");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `Passwords` ( `userId`, `Name`, `userLogin`, `securedPassword`) VALUES ( '" + u.id() + "', '" + p.name() + "', '" + p.login() + "', '" + p.securedPassword() + "');");

        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    public static ArrayList<Password> GetPasswords(int userid) {

        try {
            ArrayList<Password> P = new ArrayList<Password>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:"+DBPort+"/SPM", "root", "root");
//here sonoo is database name, root is username and password  

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Passwords where userId ='" + userid + "'");
            while (rs.next()) {
                Password p = new Password(rs.getString("Name"), rs.getString("securedPassword"), rs.getString("userLogin"));
                p.SetId(rs.getInt("id"));
                P.add(p);
            }
            con.close();
            return P;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
            // System.out.println(e);
        }
    }

    public static void DeletePassword(Password p) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:"+DBPort+"/SPM", "root", "root");
//here sonoo is database name, root is username and password  

            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `Passwords` WHERE `id` ='" + p.id() + "'");

        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    public static User GetUser(String SQL) {
        try {
            User U = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:"+DBPort+"/SPM", "root", "root");
//here sonoo is database name, root is username and password  

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                U = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("userName"), rs.getString("Password"));
            }
            con.close();
            return U;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
            // System.out.println(e);
        }
    }

}
