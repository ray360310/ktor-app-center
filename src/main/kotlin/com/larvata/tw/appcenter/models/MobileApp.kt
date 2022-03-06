package com.larvata.tw.appcenter.models

import java.util.Date
import kotlinx.serialization.Serializable

@Serializable
data class MobileApp (
    val id: String,
    val appName: String,
    val applicationId: String,
    val type: Int,
    val versionCode: Int,
    val versionName: String,
    val expiredDate: Date
)

val appDataStorage = mutableListOf<MobileApp>()