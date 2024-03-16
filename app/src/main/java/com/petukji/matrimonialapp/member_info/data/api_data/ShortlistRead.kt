package com.petukji.matrimonialapp.member_info.data.api_data

data class ShortlistReadRequest(
    val logType: String = "shortlist",
    val queryType: String = "read",
    val queryFor: String,
    val userMobile: String,
    val topQuery: String,
    val subQuery: String = "all"
)