package com.example.demo;



import java.sql.SQLException;
//import java.util.List;
import java.util.Scanner;

public class ConsoleApp extends TicketingSystemAbstract{
    private static final TicketDAO ticketDAO = new TicketDAO();
    //private static final UserDAO userDAO = new UserDAO();
    private static User currentUser;

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.print("Enter your username: ");
        String username = scanner.next();
        currentUser = ticketDAO.getUserByUsername(username);
        ConsoleApp support = new ConsoleApp();

        if (currentUser == null) {
            System.out.println("User not found. Do you want to create a new user? (Y/N)");
            String createNewUser = scanner.next();

            if ("Y".equalsIgnoreCase(createNewUser)) {
                support.createUser(scanner, username);
            } else {
                System.out.println("Exiting the application.");
//                return;
            }
        }

        System.out.println("Welcome, " + currentUser.getUsername() + "!");
        while (running) {
            support.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    support.raiseTicket(scanner);
                    break;
                case 2:
                    support.resolveTicket(scanner);
                    break;
                case 3:
                	support.viewAll();
                	break;
                case 4:
                    support.viewAllTickets();
                    break;
                case 5:
                	deleteTicketss(ticketDAO, scanner);
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Application closed.");
    }

    void displayMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("TICKETING SYSTEM FOR IT SUPPORT");
        System.out.println("1. Raise a ticket");
        System.out.println("2. Resolve a ticket");
        System.out.println("3. View all users & agents");
        System.out.println("4. View all tickets");
        System.out.println("5. Delete Ticket By UserId");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    void raiseTicket(Scanner scanner) {
        if (currentUser.isAgent()) {
            System.out.println("Agents cannot raise tickets. Only users can raise tickets.");
            return;
        }

        System.out.print("Enter the issue description: ");
        String issueDescription = scanner.nextLine();
        ticketDAO.raiseTicket(currentUser.getId(), issueDescription);
    }

    void resolveTicket(Scanner scanner) {
        if (!currentUser.isAgent()) {
            System.out.println("Only agents can resolve tickets.");
            return;
        }

        System.out.print("Enter the ticket ID to resolve: ");
        int ticketId = scanner.nextInt();
        ticketDAO.resolveTicket(ticketId);
    }

//    private static void viewAllTickets() {
//        List<Ticket> tickets = ticketDAO.getAllTickets();
//
//        if (tickets.isEmpty()) {
//            System.out.println("No tickets found.");
//            return;
//        }
//
//        System.out.println("\n===== All Tickets =====");
//        for (Ticket ticket : tickets) {
//            System.out.println(ticket);
//        }
//    }
    
//    private static void viewAll() {
//    	List<User> users = ticketDAO.getAllUsers();
//    	
//    	if(users.isEmpty()) {
//    		System.out.print("No Users found");
//    	
//    	}
//    	else {
//    		System.out.println("=====ALL=====");
//    		for(User user:users) {
//    			System.out.println(user);
//    		}
//    	}
//    }
    
    void viewAll() {
    	Scanner sc = new Scanner(System.in);
        ticketDAO.printDetails(sc); 
    }

     void viewAllTickets() {
        ticketDAO.printDetails(); 
    }

    void createUser(Scanner scanner, String username) {
        System.out.print("Enter the role (USER/AGENT): ");
        String role = scanner.next().toUpperCase();

       
        System.out.print("Enter the password: ");
        String password = scanner.next();

        ticketDAO.createUser(username, role, password);
        currentUser = ticketDAO.getUserByUsername(username);
        System.out.println("User created successfully!");
    }
    
    private static void deleteTicketss(TicketDAO ticketDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter Ticket ID: ");    	
    	int ticketId = scanner.nextInt();
        boolean isDeleted = ticketDAO.deleteTicket(ticketId);
        if (isDeleted) {
            System.out.println("Ticket with ID " + ticketId + " deleted successfully!");
        } else {
            System.out.println("Ticket with ID " + ticketId + " not found or could not be deleted!");
        }	
    }    
}

