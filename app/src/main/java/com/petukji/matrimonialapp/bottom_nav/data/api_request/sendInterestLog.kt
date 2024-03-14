package com.petukji.matrimonialapp.bottom_nav.data.api_request

// Data class for request payload
data class StatusLogRequest(
    val logType: String,
    val queryType: String, // You can define an enum for Read, Write, Update
    val queryFor: String, // You can define an enum for byMe, forMe
    val userMobile: String,
    val topQuery: String, // You can define an enum for Count, Detail
    val subQuery: String, // You can define an enum for All, Date, Time, DateTime
    val dateQuery: String? = null,
    val timeQuery: String? = null,
    val dateTimeQuery: String? = null,
    val data: Any? = null
)

// Data class for response payload
data class StatusLogResponse(
    val count: Int? = null,
    val data: List<Any>? = null,
    val message: String? = null,
    val error: String? = null
)

// Data class for a single log entry
data class LogEntry(
    val by: String,
    val byName: String,
    val byProfile: String,
    val byAge: Int,
    val byLocation: String,
    val byShortDesc: String,
    val byProfileNeed: String,
    val date: String,
    val time: String,
    val to: String,
    val toName: String,
    val toProfile: String,
    val toAge: Int,
    val toLocation: String,
    val toShortDesc: String,
    val toProfileLogic: String
)

// Data class for a collection of log entries
data class LogCollection(
    val logs: Map<String, LogEntry>
)

