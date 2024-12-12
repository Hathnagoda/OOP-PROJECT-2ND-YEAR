package io.bootify.ticket_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.Set;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 * Entity class representing the "Vendors" table in the database.
 * This class stores information about vendors responsible for issuing tickets.
 */

@Entity
@Table(name = "Vendors")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    public Vendor(String name) {
        this.name = name;
    }
    /**
     * Primary key for the Vendor entity.
     * Automatically generated using the IDENTITY strategy by the database.
     */

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the vendor.
     * This field is mandatory.
     */


    @Column(nullable = false)
    private String name;
    /**
     * One-to-Many relationship with the Ticket entity.
     * Represents all tickets issued by this vendor.
     * The relationship is mapped using the "vendorId" field in the Ticket entity.
     */

    @OneToMany(mappedBy = "vendorId")
    private Set<Ticket> tickets;
    /**
     * Total number of tickets issued by the vendor.
     */

    @Column
    private Integer totalTickets;
    /**
     * The rate at which tickets are released by the vendor.
     */

    @Column
    private Integer ticketReleaseRate;
    /**
     * The maximum ticket capacity that the vendor can handle.
     */

    @Column
    private Integer maxTicketCapacity;
    /**
     * Timestamp for when the vendor record was created.
     * Automatically populated by Spring JPA auditing.
     */

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;
    /**
     * Timestamp for when the vendor record was last updated.
     * Automatically populated by Spring JPA auditing.
     */

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
