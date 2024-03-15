package com.petukji.matrimonialapp.bottom_nav.data.api_data.user

data class UserProfile(
    val mobileKey: String = "",
    val userID: String = "",
    val profileImagePath: String = "",
    val longDescription: String = "",
    val age: String = "",
    val nationality: String = "",
    val religion: String = "",
    val zodiac: String = "",
    val correspondenceCity: String = "",
    val correspondenceState: String = "",
    val correspondenceCountry: String = "",
    val permanentCity: String = "",
    val permanentState: String = "",
    val permanentCountry: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val isPaidMember: String = "",
    val profileCurrentStatus: String = "",
    val bodyColor: String = "",
    val bodyType: String = "",
    val marriagePreference: String = "",
    val interests: String = "",
    val badHabit: String = "",
    val occupation: String = "",
    val highestQualification: String = "",
    val companyName: String = "",
    val companyAddress: String = "",
    val totalFamily: String = "",
    val isJointFamily: String = "",
    val memberLiveTogether: String = "",
    val areYouLivingWithFamily: String = ""
)

data class UserProfileRequest(
    val mobileKey:String
)
