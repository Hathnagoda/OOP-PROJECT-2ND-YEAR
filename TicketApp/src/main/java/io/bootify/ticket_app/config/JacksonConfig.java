package io.bootify.ticket_app.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JacksonConfig {
    /**
     * Bean definition to customize Jackson's ObjectMapper settings.
     *
     * @return a Jackson2ObjectMapperBuilderCustomizer instance with specified configurations.
     */

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                // Disables specific features in Jackson for JSON processing
                .featuresToDisable(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        DeserializationFeature.ACCEPT_FLOAT_AS_INT,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
                );
    }

}
