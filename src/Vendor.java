public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int vendorId;
    private final int ticketReleaseInterval; // Rate as seconds

    public Vendor(TicketPool ticketPool, int vendorId, int ticketReleaseInterval) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.ticketReleaseInterval = ticketReleaseInterval;
    }

    @Override
    public void run() {
        int ticketNumber = 1;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (ticketPool) {
                    if (ticketPool.isTotalTicketsLimitReached()) {
                        System.out.println("Vendor " + vendorId + " stopping. Total tickets limit reached.");
                        return;
                    }

                    if (ticketPool.getTicketCount() >= ticketPool.maxCapacity) {
                        ticketPool.wait(); // Wait if the pool is full
                    }
                }

                String ticket = "Vendor" + vendorId + "-Ticket" + ticketNumber++;
                ticketPool.addTicket(ticket);

                synchronized (System.out) {
                    System.out.println("Vendor " + vendorId + " added: " + ticket);
                }

                // Sleep for the input interval (seconds converted to milliseconds)
                Thread.sleep(ticketReleaseInterval * 1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor " + vendorId + " stopped.");
        }
    }
}
