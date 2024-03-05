package com.petukji.marrageportal

data class UsersPreference(
    val age: String,
    val correspondenceCity: String,
    val correspondenceCountry: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val mobile: String,
    val mobileKey: String,
    val profileImagePath: String,
    val searchingForDescription: String,
    val shortDescription: String,
    val userID: String
)