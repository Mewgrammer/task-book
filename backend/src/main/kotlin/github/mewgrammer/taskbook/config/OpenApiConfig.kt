package github.mewgrammer.taskbook.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.servers.Server

@OpenAPIDefinition(
    info = Info(
        title = "Taskbook API",
        version = "0.0.1-SNAPSHOT",
        description = "API for a sample project which randomly mixes Kotlin and Java classes to show the interoperability capabilities. Also includes openapi client generation for a Vue3 UI",
        contact = Contact(
            name = "Mewgrammer",
            url = "https://github.com/Mewgrammer",
        ),
        license = License(
            name = "MIT Licence",
            url = "https://github.com/thombergs/code-examples/blob/master/LICENSE"
        )
    ),
    servers = [Server(url = "http://localhost:8080")]
)
@SecurityScheme(
    name = "api",
    scheme = "basic",
    type = SecuritySchemeType.HTTP,
    `in` = SecuritySchemeIn.HEADER
)
class OpenApiConfig {
}