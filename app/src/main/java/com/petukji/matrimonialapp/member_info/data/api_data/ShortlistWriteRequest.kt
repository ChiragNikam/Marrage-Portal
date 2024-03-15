package com.petukji.matrimonialapp.member_info.data.api_data

data class ShortlistWriteRequest(
    val logType: String = "shortlist",
    val queryType: String = "write",
    val data:ShortListLogData
)

data class ShortListLogData(
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

data class  ShortListLogDataResponse(
    val message:String
)