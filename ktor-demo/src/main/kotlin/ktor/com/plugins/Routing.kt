package ktor.com.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(AutoHeadResponse)
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/post") {
            call.respondText("post", ContentType.Application.Json, HttpStatusCode.OK)
        }
    }
}
