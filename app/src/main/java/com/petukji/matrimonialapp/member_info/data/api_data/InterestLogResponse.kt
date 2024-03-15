package com.petukji.matrimonialapp.member_info.data.api_data

data class InterestLogResponse(
    val data: List<UserLog>
)

data class UserLog(
    val by: String,
    val byAge: Int,
    val byLocation: String,
    val byName: String,
    val byProfile: String,
    val byProfileNeed: String,
    val byShortDesc: String,
    val date: String,
    val time: String,
    val to: String,
    val toAge: Int,
    val toLocation: String,
    val toName: String,
    val toProfile: String,
    val toProfileLogic: String,
    val toShortDesc: String
)