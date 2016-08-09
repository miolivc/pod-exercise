/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.exercise5.server;

import io.github.exercise3.enums.Nodes;
import io.github.exercise3.interfaces.Op1;
import io.github.exercise5.controllers.Controller;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class Server {

    public void start() {

        try {

            Op1 node1Controller = new Controller();

            try {
                LocateRegistry.createRegistry(4889);
            } catch (Exception ex) {
            }

            Naming.rebind("//localhost:"+Nodes.NODE1.getRegistryName(), node1Controller);

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
