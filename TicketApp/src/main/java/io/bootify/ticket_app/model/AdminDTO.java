package io.bootify.ticket_app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
/**
 * Data Transfer Object (DTO) for the Admin entity.
 * Used to encapsulate data to be transferred between different layers of the application,
 * ensuring separation of concerns and data validation.
 */

@Getter
@Setter
public class AdminDTO {

    private Long id;
    /**
     * Name of the Admin.
     * - Must not be null.
     * - Must have a maximum length of 255 characters.
     */

    @NotNull
    @Size(max = 255)
    private String name;

}
