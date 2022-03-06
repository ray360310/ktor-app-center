package com.larvata.tw.appcenter

import com.larvata.tw.appcenter.routes.registerMobileAppRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerMobileAppRoutes()
}
