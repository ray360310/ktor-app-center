package com.larvata.tw.appcenter.routes

import com.larvata.tw.appcenter.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.mobileAppRouting() {
    route("/mobileApp") {
        get {
            if (appDataStorage.isNotEmpty()) {
                call.respond(appDataStorage)
            }else {
                call.respondText("No Apps Found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val mobileApp = appDataStorage.find { it.id == id } ?: return@get call.respondText(
                "No mobileApp with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(mobileApp)
        }
        post {
            val mobileApp = call.receive<MobileApp>()
            appDataStorage.add(mobileApp)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (appDataStorage.removeIf { it.id == id }) {
                call.respondText("MobileApp removed correctly", status = HttpStatusCode.Accepted)
            }else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun Application.registerMobileAppRoutes() {
    routing {
        mobileAppRouting()
    }
}