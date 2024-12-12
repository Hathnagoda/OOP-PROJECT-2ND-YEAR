package io.bootify.ticket_app.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entity class representing the "Customers" table in the database.
 * This class maps the database table columns to Java fields and includes
 * relationships to other entities (e.g., tickets) and auditing capabilities.
 */
@Entity
@Table(name = "Customers")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    public Customer(String name) {
        this.name = name;
    }

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "customerId")
    // Defines a one-to-many relationship between Customer and Ticket entities
    // The "mappedBy" attribute indicates that the "customerId" field in the Ticket entity owns the relationship
    private Set<Ticket> tickets;

    @Column
    private Integer totalTickets;

    @Column
    private Integer customerRetrievalRate;

    @Column
    private Integer maxTicketCapacity;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    // Indicates this field will store the creation timestamp, automatically managed by Spring Data JPA
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    // Indicates this field will store the last modified timestamp, automatically managed by Spring Data JPA
    private OffsetDateTime lastUpdated;

}
