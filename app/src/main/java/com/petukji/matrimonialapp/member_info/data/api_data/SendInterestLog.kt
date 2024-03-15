package com.petukji.matrimonialapp.member_info.data.api_data

// Data class for request payload
data class StatusLogRequest(
    val logType: String = "interest",
    val queryType: String = "write",
    val data: LogEntry
)

// Data class for a single log entry
data class LogEntry(
    val by: String,
    val byName: String,
    val byAge: String,
    val byLocation: String,
    val byShortDesc: String,
    val date: String,
    val time: String,
    val to: String,
    val toName: String,
    val toAge: String,
    val toLocation: String,
    val toShortDesc: String
)
