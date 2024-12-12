package io.bootify.ticket_app.repos;

import io.bootify.ticket_app.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and manipulating Vendor entities in the database.
 * This interface extends JpaRepository, providing standard CRUD operations for Vendor.
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
