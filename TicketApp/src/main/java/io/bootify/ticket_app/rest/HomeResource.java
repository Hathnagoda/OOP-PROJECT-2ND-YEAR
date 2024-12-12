package io.bootify.ticket_app.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeResource is a REST controller that handles requests to the root URL.
 */
@RestController
public class HomeResource {

    @GetMapping("/")
    public String index() {
        return "\"Hello World!\"";
    }

}
