package io.bootify.ticket_app.repos;

import io.bootify.ticket_app.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and manipulating Customer entities in the database.
 * This interface extends JpaRepository, providing a set of CRUD operations on Customer entities.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
