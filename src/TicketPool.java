import java.util.LinkedList;

public class TicketPool {
    private final LinkedList<String> tickets = new LinkedList<>();
    final int maxCapacity;       // Maximum tickets the pool can hold
    private final int totalTicketsLimit; // Total tickets vendors can add
    private int totalTicketsAdded = 0;   // Counter for tickets added
    private int totalTicketsSold = 0;    // Counter for tickets sold

    public TicketPool(int maxCapacity, int totalTicketsLimit) {
        this.maxCapacity = maxCapacity;
        this.totalTicketsLimit = totalTicketsLimit;
    }

    public synchronized void addTicket(String ticket) throws InterruptedException {
        // Stop adding if total tickets limit is reached
        if (totalTicketsAdded >= totalTicketsLimit) {
            return;
        }

        while (tickets.size() >= maxCapacity) {
            wait(); // Wait if the pool is full
        }

        tickets.add(ticket);
        totalTicketsAdded++;
        System.out.println("Ticket Added: " + ticket + " | Current Pool: " + tickets.size());
        notifyAll(); // Notify customers
    }

    public synchronized String removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait(); // Wait if the pool is empty
        }

        String ticket = tickets.removeFirst();
        totalTicketsSold++;
        System.out.println("Ticket Sold: " + ticket + " | Current Pool: " + tickets.size());
        notifyAll(); // Notify vendors
        return ticket;
    }

    public synchronized int getTicketCount() {
        return tickets.size();
    }

    public synchronized int getTotalTicketsAdded() {
        return totalTicketsAdded;
    }

    public synchronized int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public synchronized boolean isTotalTicketsLimitReached() {
        return totalTicketsAdded >= totalTicketsLimit;
    }
}
