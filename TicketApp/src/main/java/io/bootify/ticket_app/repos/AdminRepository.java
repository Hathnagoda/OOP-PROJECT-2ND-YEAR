package io.bootify.ticket_app.repos;

import io.bootify.ticket_app.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for accessing and manipulating Admin entities in the database.
 * This interface extends JpaRepository, providing a set of CRUD operations on Admin entities.
 */

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
