public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int vendorId;
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int vendorId, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        int ticketNumber = 1;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (ticketPool) {
                    // Stop if total tickets limit is reached
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

                Thread.sleep(1000 / ticketReleaseRate);
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor " + vendorId + " stopped.");
        }
    }
}
