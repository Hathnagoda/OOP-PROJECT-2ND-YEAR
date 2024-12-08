public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerId;
    private final int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerId, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String ticket = ticketPool.removeTicket();
                synchronized (System.out) { // Synchronize logging
                    System.out.println("Customer " + customerId + " purchased: " + ticket);
                }
                Thread.sleep(1000 / customerRetrievalRate); // Control retrieval rate
            }
        } catch (InterruptedException e) {
            synchronized (System.out) {
                System.out.println("Customer " + customerId + " stopped.");
            }
        }
    }
}
