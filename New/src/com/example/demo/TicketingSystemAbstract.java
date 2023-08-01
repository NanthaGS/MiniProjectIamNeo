package com.example.demo;

import java.util.Scanner;

public abstract class TicketingSystemAbstract {
	protected static final TicketDAO ticketDAO = new TicketDAO();
    protected static final UserDAO userDAO = new UserDAO();
    protected static User currentUser;

    
    abstract void displayMenu();
    
    abstract void raiseTicket(Scanner sc);
    
    abstract void resolveTicket(Scanner sc);
    
    abstract void viewAllTickets();
    
    abstract void viewAll();
    
    abstract void createUser(Scanner sc,String str);

}
