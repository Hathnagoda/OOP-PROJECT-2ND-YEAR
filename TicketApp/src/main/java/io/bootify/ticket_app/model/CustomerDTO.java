package io.bootify.ticket_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data Transfer Object (DTO) for the Customer entity.
 * This class is used to transfer customer data between the application layers.
 * It simplifies data handling by encapsulating fields and ensuring consistency.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    /**
     * Unique identifier for the Customer.
     * Maps to the primary key in the Customer entity.
     */

    private Long id;

    private String name;

    private Integer totalTickets;
    /**
     * Maximum ticket capacity for the Customer.
     * This could be a limit based on system or business logic.
     */

    private Integer customerRetrievalRate;

    private Integer maxTicketCapacity;

}
