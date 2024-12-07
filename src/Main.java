import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Thread> vendors = new ArrayList<>();
        List<Thread> customers = new ArrayList<>();

        System.out.println("Welcome to the Real-Time Ticketing System CLI");

        // Prompt for total tickets and maximum capacity
        int totalTickets;
        while (true) {
            System.out.print("Enter Total Tickets: ");
            totalTickets = scanner.nextInt();
            if (totalTickets > 0) break;
            System.out.println("Total Tickets must be greater than 0. Please try again.");
        }

        int maxCapacity;
        while (true) {
            System.out.print("Enter Max Ticket Capacity: ");
            maxCapacity = scanner.nextInt();
            if (maxCapacity > 0) break;
            System.out.println("Max Ticket Capacity must be greater than 0. Please try again.");
        }

        int ticketReleaseRate;
        while (true) {
            System.out.print("Enter Ticket Release Rate (tickets/second): ");
            ticketReleaseRate = scanner.nextInt();
            if (ticketReleaseRate > 0) break;
            System.out.println("Ticket Release Rate must be greater than 0. Please try again.");
        }

        int customerRetrievalRate;
        while (true) {
            System.out.print("Enter Customer Retrieval Rate (tickets/second): ");
            customerRetrievalRate = scanner.nextInt();
            if (customerRetrievalRate > 0) break;
            System.out.println("Customer Retrieval Rate must be greater than 0. Please try again.");
        }

        // Initialize ticket pool
        TicketPool ticketPool = new TicketPool(maxCapacity, totalTickets);

        // Menu-driven interaction
        while (true) {
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
                    for (int i = 1; i <= vendorCount; i++) {
                        Thread vendor = new Thread(new Vendor(ticketPool, i, ticketReleaseRate));
                        vendors.add(vendor);
                        vendor.start();
                    }
                    System.out.println(vendorCount + " vendors started.");
                }
                case 2 -> {
                    System.out.print("Enter number of customers to start: ");
                    int customerCount = scanner.nextInt();
                    for (int i = 1; i <= customerCount; i++) {
                        Thread customer = new Thread(new Customer(ticketPool, i, customerRetrievalRate));
                        customers.add(customer);
                        customer.start();
                    }
                    System.out.println(customerCount + " customers started.");
                }
                case 3 -> {
                    synchronized (ticketPool) {
                        System.out.println("Tickets in Pool: " + ticketPool.getTicketCount());
                        System.out.println("Total Tickets Added: " + ticketPool.getTotalTicketsAdded());
                        System.out.println("Total Tickets Sold: " + ticketPool.getTotalTicketsSold());
                    }
                }
                case 4 -> {
                    System.out.println("Stopping all operations...");
                    vendors.forEach(Thread::interrupt);
                    customers.forEach(Thread::interrupt);
                    vendors.clear();
                    customers.clear();
                    System.out.println("All vendors and customers stopped.");
                }
                case 5 -> {
                    System.out.println("\nSystem Summary:");
                    synchronized (ticketPool) {
                        System.out.println("Total Tickets Added: " + ticketPool.getTotalTicketsAdded());
                        System.out.println("Total Tickets Sold: " + ticketPool.getTotalTicketsSold());
                        System.out.println("Final Tickets in Pool: " + ticketPool.getTicketCount());
                    }
                    System.out.println("Exiting system. Goodbye!");
                    vendors.forEach(Thread::interrupt);
                    customers.forEach(Thread::interrupt);
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
