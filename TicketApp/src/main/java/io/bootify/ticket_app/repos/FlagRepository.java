package io.bootify.ticket_app.repos;

import io.bootify.ticket_app.domain.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for accessing and manipulating Flag entities in the database.
 * This interface extends JpaRepository, providing a set of CRUD operations on Flag entities.
 */
public interface FlagRepository extends JpaRepository<Flag , Long> {
}
