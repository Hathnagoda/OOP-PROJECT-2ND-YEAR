import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Getters and Setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = validateInput(totalTickets, "Total Tickets");
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = validateInput(ticketReleaseRate, "Ticket Release Rate");
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = validateInput(customerRetrievalRate, "Customer Retrieval Rate");
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = validateInput(maxTicketCapacity, "Max Ticket Capacity");
    }

    // Validate user input
    private int validateInput(int value, String parameterName) {
        while (value <= 0) {
            System.out.println(parameterName + " must be greater than 0. Please try again:");
            value = new Scanner(System.in).nextInt();
        }
        return value;
    }
}
