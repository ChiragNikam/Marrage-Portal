package com.petukji.matrimonialapp.member_info.data.api_data

data class ViewLogWriteRequest(
    val logType: String = "view",
    val queryType: String = "write",
    val data: LogData
)

data class LogData(
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

data class LogDataResponse(
    val message:String
)