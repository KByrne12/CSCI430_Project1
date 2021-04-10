package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.text.*;
import java.io.*;

public class LoginState extends WareState implements ActionListener {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static LoginState instance;
    private static final int LOGOUT = 0;
    private static final int CLIENT = 1;
    private static final int CLERK = 2;
    private static final int MANAGER = 3;
    private static final int HELP = 4;
    private AbstractButton clientButton,clerkButton,managerButton,logoutButton,any;
    //private ClientButton clientButton;
    //private ClerkButton clerkButton;
    //private ManagerButton managerButton;
    //private LogoutButton logoutButton;
    private JFrame frame;
    private WareContext context;

    private LoginState() {
        super();
        //clientButton = new ClientButton();
        //clientButton.setListener();
        //((ClientButton)clientButton).setListener();
        //clerkButton.setListener();
       //((ClerkButton)clerkButton).setListener();
        //managerButton.setListener();
       //((ManagerButton)managerButton).setListener();
        //logoutButton.setListener();
    }

    public static LoginState instance() {
        if (instance == null) {
            instance = new LoginState();
        }
        return instance;
    }

    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= LOGOUT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    public void clear() { //clean up stuff
        frame.getContentPane().removeAll();
        frame.paint(frame.getGraphics());
    }

    public void help() {
        System.out.println("Enter a number between 0 and 3 as explained below:");
        System.out.println(LOGOUT + " to Logout\n");
        System.out.println(CLIENT + " to login as a client.");
        System.out.println(CLERK + " to login as a clerk.");
        System.out.println(MANAGER + " to login as a manager.");
    }

    public void client() {
        clear();
        int UID = Integer.parseInt(getToken("Enter the ID of the client to login as: "));
        (WareContext.instance()).setUID(UID);
        (WareContext.instance()).changeState(1);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(this.clientButton))
        {//System.out.println("user \n");
            this.client();}
        else if (event.getSource().equals(this.logoutButton))
            this.logout();
        else if (event.getSource().equals(this.clerkButton))
            this.clerk();
        else if (event.getSource().equals(this.managerButton))
            this.manager();
    }

    public void clerk() {
        clear();
        (WareContext.instance()).changeState(2);
    }

    public void manager() {
        clear();
        (WareContext.instance()).changeState(3);
    }

    public void logout() {
        clear();
        (WareContext.instance()).changeState(0);
    }

    public void process() {
        frame = (WareContext.instance()).getFrame();
        clear();
        //frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new FlowLayout());
        clientButton = new JButton("Client");
        clerkButton = new JButton("Clerk");
        managerButton = new JButton("Manager");
        logoutButton = new JButton("Logout");
        clientButton.addActionListener(this);
        clerkButton.addActionListener(this);
        managerButton.addActionListener(this);
        logoutButton.addActionListener(this);
        frame.getContentPane().add(this.clientButton);
        frame.getContentPane().add(this.clerkButton);
        frame.getContentPane().add(this.managerButton);
        frame.getContentPane().add(this.logoutButton);
        frame.setVisible(true);
        frame.paint(frame.getGraphics());
        frame.toFront();
        frame.requestFocus();
    }

    /* previous process
    int command;
        help();
        while ((command = getCommand()) != LOGOUT){
            switch (command) {
                case CLIENT:
                    client();
                    break;
                case CLERK:
                    clerk();
                    break;
                case MANAGER:
                    manager();
                    break;
                default:
                    System.out.println("Not a valid command");
            }
        }
        logout();

     */

    public void run() {
        process();
    }

}