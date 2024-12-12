package io.bootify.ticket_app.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for the Vendor entity.
 * This class is used to transfer vendor-related data between different layers of the application.
 * It encapsulates information about a vendor, including ticket-related details.
 */
@Getter
@Setter
public class VendorDTO {
    /**
     * Unique identifier for the Vendor.
     * Maps to the primary key in the Vendor entity.
     */

    private Long id;

    private String name;

    private Integer totalTickets;
    /**
     * Ticket release rate of the Vendor.
     * This indicates how quickly or frequently tickets are released by the vendor.
     */

    private Integer ticketReleaseRate;
    /**
     * Maximum ticket capacity of the Vendor.
     * Represents the upper limit of the number of tickets the vendor can handle or sell.
     */

    private Integer maxTicketCapacity;

}
