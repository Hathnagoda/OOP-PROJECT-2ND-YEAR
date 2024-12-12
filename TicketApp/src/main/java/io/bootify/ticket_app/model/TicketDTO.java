package io.bootify.ticket_app.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for the Ticket entity.
 * This class is used to transfer ticket-related data between different layers of the application.
 * It encapsulates information about a ticket, including associated vendor and customer details.
 */
@Getter
@Setter
public class TicketDTO {

    /**
     * Unique identifier for the Ticket.
     * Maps to the primary key in the Ticket entity.
     */

    private Long id;

    private Long vendorId;

    private VendorDTO vendor;

    private Long customerId;

    /**
     * Customer associated with the Ticket.
     * This field holds a reference to the CustomerDTO, providing more detailed information about the customer.
     */

    private CustomerDTO customer;

    /**
     * Number of tickets.
     * This field represents the quantity of tickets in the given ticket transaction.
     */

    private Integer count;

}
