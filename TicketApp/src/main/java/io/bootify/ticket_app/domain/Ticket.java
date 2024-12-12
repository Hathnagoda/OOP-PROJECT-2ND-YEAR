package io.bootify.ticket_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entity class representing the "Tickets" table in the database.
 * This class is used to store information about tickets issued to customers by vendors.
 */
@Entity
@Table(name = "Tickets")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    public Ticket(Vendor vendorId, Customer customerId, Integer count) {
        this.vendorId = vendorId;
        this.customerId = customerId;
        this.count = count;
    }

    /**
     * Primary key for the Ticket entity.
     * This is auto-generated using the IDENTITY strategy by the database.
     */

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Many-to-One relationship with the Vendor entity.
     * FetchType.LAZY ensures the vendor data is loaded only when accessed.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendorId;
    /**
     * Many-to-One relationship with the Customer entity.
     * FetchType.LAZY ensures the customer data is loaded only when accessed.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    /**
     * The count of tickets issued in this record.
     */

    @Column
    private Integer count;
    /**
     * Timestamp for when the ticket record was created.
     * Automatically populated by Spring JPA auditing.
     */

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;
    /**
     * Timestamp for when the ticket record was last updated.
     * Automatically populated by Spring JPA auditing.
     */

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;
}
