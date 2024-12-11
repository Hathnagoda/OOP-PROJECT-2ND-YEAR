public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerId;
    private final int customerRetrievalInterval; // Rate as seconds

    public Customer(TicketPool ticketPool, int customerId, int customerRetrievalInterval) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.customerRetrievalInterval = customerRetrievalInterval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String ticket;
                synchronized (ticketPool) {
                    if (ticketPool.getTicketCount() == 0) {
                        ticketPool.wait(); // Wait if the pool is empty
                    }
                    ticket = ticketPool.removeTicket();
                }

                synchronized (System.out) {
                    System.out.println("Customer " + customerId + " purchased: " + ticket);
                }

                // Sleep for the input interval (seconds converted to milliseconds)
                Thread.sleep(customerRetrievalInterval * 1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Customer " + customerId + " stopped.");
        }
    }
}
