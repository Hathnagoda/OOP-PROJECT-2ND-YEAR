import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Method to log messages to a file
    private static synchronized void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SystemLog.txt", true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    // Method to log a separator for better readability
    private static synchronized void logSeparator() {
        logToFile("------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Thread> vendors = new ArrayList<>();
        List<Thread> customers = new ArrayList<>();

        System.out.println("Welcome to the Real-Time Ticketing System CLI");
        logToFile("Welcome to the Real-Time Ticketing System CLI");
        logSeparator();

        int totalTickets = 0;
        int maxCapacity = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;

        // Prompt for Total Tickets
        while (true) {
            try {
                System.out.print("Enter Total Tickets: ");
                totalTickets = scanner.nextInt();
                if (totalTickets > 0) break;
                System.out.println("Total Tickets must be greater than 0. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                scanner.next();
            }
        }
        logToFile("Total Tickets: " + totalTickets);

        // Prompt for Max Ticket Capacity
        while (true) {
            try {
                System.out.print("Enter Max Ticket Capacity: ");
                maxCapacity = scanner.nextInt();
                if (maxCapacity > 0) break;
                System.out.println("Max Ticket Capacity must be greater than 0. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                scanner.next();
            }
        }
        logToFile("Max Ticket Capacity: " + maxCapacity);

        // Prompt for Ticket Release Rate
        while (true) {
            try {
                System.out.print("Enter Ticket Release Rate (tickets/second): ");
                ticketReleaseRate = scanner.nextInt();
                if (ticketReleaseRate > 0) break;
                System.out.println("Ticket Release Rate must be greater than 0. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                scanner.next();
            }
        }
        logToFile("Ticket Release Rate: " + ticketReleaseRate);

        // Prompt for Customer Retrieval Rate
        while (true) {
            try {
                System.out.print("Enter Customer Retrieval Rate (tickets/second): ");
                customerRetrievalRate = scanner.nextInt();
                if (customerRetrievalRate > 0) break;
                System.out.println("Customer Retrieval Rate must be greater than 0. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                scanner.next();
            }
        }
        logToFile("Customer Retrieval Rate: " + customerRetrievalRate);
        logSeparator();

        // Initialize ticket pool
        TicketPool ticketPool = new TicketPool(maxCapacity, totalTickets);

        // Menu-driven interaction
        while (true) {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Start Vendors");
                System.out.println("2. Start Customers");
                System.out.println("3. View Real-Time Ticket Pool Status");
                System.out.println("4. Stop All Operations");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter number of vendors to start: ");
                        int vendorCount = scanner.nextInt();
                        if (vendorCount <= 0) {
                            System.out.println("Number of vendors must be greater than 0.");
                            break;
                        }
                        for (int i = 1; i <= vendorCount; i++) {
                            Thread vendor = new Thread(new Vendor(ticketPool, i, ticketReleaseRate));
                            vendors.add(vendor);
                            vendor.start();
                        }
                        System.out.println(vendorCount + " vendors started.");
                        logToFile(vendorCount + " vendors started.");
                    }
                    case 2 -> {
                        System.out.print("Enter number of customers to start: ");
                        int customerCount = scanner.nextInt();
                        if (customerCount <= 0) {
                            System.out.println("Number of customers must be greater than 0.");
                            break;
                        }
                        for (int i = 1; i <= customerCount; i++) {
                            Thread customer = new Thread(new Customer(ticketPool, i, customerRetrievalRate));
                            customers.add(customer);
                            customer.start();
                        }
                        System.out.println(customerCount + " customers started.");
                        logToFile(customerCount + " customers started.");
                    }
                    case 3 -> {
                        synchronized (ticketPool) {
                            String status = "Tickets in Pool: " + ticketPool.getTicketCount() +
                                    ", Total Tickets Added: " + ticketPool.getTotalTicketsAdded() +
                                    ", Total Tickets Sold: " + ticketPool.getTotalTicketsSold();
                            System.out.println(status);
                            logToFile(status);
                        }
                    }
                    case 4 -> {
                        System.out.println("Stopping all operations...");
                        logToFile("Stopping all operations...");
                        vendors.forEach(Thread::interrupt);
                        customers.forEach(Thread::interrupt);
                        vendors.clear();
                        customers.clear();
                        System.out.println("All vendors and customers stopped.");
                        logToFile("All vendors and customers stopped.");
                        logSeparator();
                    }
                    case 5 -> {
                        System.out.println("\nSystem Summary:");
                        synchronized (ticketPool) {
                            String summary = "Total Tickets Added: " + ticketPool.getTotalTicketsAdded() +
                                    ", Total Tickets Sold: " + ticketPool.getTotalTicketsSold() +
                                    ", Final Tickets in Pool: " + ticketPool.getTicketCount();
                            System.out.println(summary);
                            logToFile(summary);
                        }
                        System.out.println("Exiting system. Goodbye!");
                        logToFile("Exiting system. Goodbye!");
                        logSeparator();
                        vendors.forEach(Thread::interrupt);
                        customers.forEach(Thread::interrupt);
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice. Please select an option between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                logToFile("Invalid input! Error: " + e.getMessage());
                scanner.next();
            }
        }
    }
}
