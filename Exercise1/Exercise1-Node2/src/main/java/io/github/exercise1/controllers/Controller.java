/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.exercise1.controllers;

import io.github.exercise1.interfaces.Node2;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class Controller extends UnicastRemoteObject implements Node2, Serializable {
    
    public Controller() throws RemoteException{
        super();
    }

    @Override
    public int getTotalUsers() throws RemoteException{

        int total = -1;

        Connection connection = null;
        CallableStatement call = null;

        try {

            connection = getConnection();
            call = connection.prepareCall("SELECT count(*) AS total_users FROM users");

            ResultSet resultSet = call.executeQuery();

            if (resultSet.next()) {
                total = resultSet.getInt("total_users");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        try {

            call.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace();
        }

        return total;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pod", "root", "victor");
    }

}
