package com.mvs.service

import io.bkbn.kompendium.Kompendium
import io.bkbn.kompendium.models.oas.OpenApiSpecInfo
import io.bkbn.kompendium.models.oas.OpenApiSpecServer
import java.net.URI

val host = "192.168.240.165"
val port = "8089"
val oas = Kompendium.openApiSpec.copy(
  info = OpenApiSpecInfo(
    title = "Microservice API",
    version = "1.33.7",
    description = "Microservice generated API spec",
    //termsOfService = URI("https://example.com"),
    /*contact = OpenApiSpecInfoContact(
      name = "Homer Simpson",
      email = "chunkylover53@aol.com",
      url = URI("https://gph.is/1NPUDiM")
    ),*/
    /*license = OpenApiSpecInfoLicense(
      name = "MIT",
      url = URI("https://github.com/bkbnio/kompendium/blob/main/LICENSE")
    )*/
  ),
  servers = mutableListOf(
    OpenApiSpecServer(
      url = URI("http://$host:$port"),
      description = "Production instance of my API"
    ),
    OpenApiSpecServer(
      url = URI("http://$host:$port"),
      description = "Where the fun stuff happens"
    )
  )
)
