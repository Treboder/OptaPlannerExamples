package treboder.optaplanner.examples.timetable.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(

        info = @Info(
                title = "Magic Mosaic API",
                description = "Core Services",
                contact = @Contact(name = "Treboder", url = "magicmosaic.art", email = "treboder@gmail.com"),
                license = @License(name = "All rights reserved", url = "magicmosaic.art")),

        servers = { @Server(url = "http://localhost:8080", description = "Localhost") }
)

class OpenAPIConfiguration {
}