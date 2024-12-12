package io.bootify.ticket_app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing the "Flag" table in the database.
 * This class is used to store flag-related information, such as status.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flag {

    /**
     * Primary key for the Flag entity.
     * This is auto-generated using the IDENTITY strategy by the database.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The status of the flag.
     * This field stores an integer value representing the flag's current state.
     */

    private  Integer status;
}
