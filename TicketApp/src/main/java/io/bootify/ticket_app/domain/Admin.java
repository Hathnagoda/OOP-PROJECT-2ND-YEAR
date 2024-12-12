package io.bootify.ticket_app.domain;
// Importing necessary classes and annotations for entity and auditing functionality

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entity class representing the "Admins" table in the database.
 * This class is used to map the database table columns to fields in Java.
 * It includes auditing fields for tracking creation and last update times.
 */

@Entity
@Table(name = "Admins")
// Maps this class to the "Admins" table in the database
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Admin {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreatedDate
    // Ensures the column is non-null and cannot be updated
    @Column(nullable = false, updatable = false)
    // Ensures the column is non-null and cannot be updated
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
