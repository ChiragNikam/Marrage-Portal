package com.petukji.matrimonialapp.auth.data.api_data

data class RegistrationRequestData(
    val mobileKey: String="",
    val userID: String="",
    val mobile: String="",
    val email: String="",
    val firstName: String="",
    val lastName: String="",
    val fullName: String="",
    val profileImagePath: String = " ",
    val shortDescription: String = " ",
    val searchingForDescription: String = " ",
    val longDescription: String = " ",
    val profileCreatedBy: String = " ",
    val gender: String="",
    val zodiac: String = " ",
    val religion: String = " ",
    val cast: String = " ",
    val subCast: String = " ",
    val correspondenceCountry: String = " ",
    val permanentCountry: String = " ",
    val correspondenceCity: String = " ",
    val permanentCity: String = " ",
    val correspondenceState: String = " ",
    val permanentState: String = " ",
    val nationality: String = " ",
    val age: String = " ",
    val nationalityType: String = " ",
    val qualification: String = " ",
    val profileCreationDate: String = " ",
    val profileCreationTime: String = " ",
    val profileCurrentStatus: String = " ",
    val profileStatus: String = " ",
    val isPaidMember: Boolean = false,
    val paidStartDate: String = " ",
    val paidExpireDate: String = " ",
    val correspondenceLAT: String = " ",
    val correspondenceLONG: String = " ",
    val badHabit: String = " ",
    val habitSeverity: String = " ",
    val interests: String = " ",
    val dietPreference: String = " ",
    val password: String = " ",
    val dob: String = " ",
    val timeOfBirth: String = " ",
    val placeOfBirth: String = " ",
    val bodyColor: String = " ",
    val height: String = " ",
    val bodyType: String = " ",
    val zodiacSign: String = " ",
    val maritalStatus: String = " ",
    val caste: String = " ",
    val subCommunity: String = " ",
    val motherTongue: String = " ",
    val languagesKnown: String = " ",
    val doshan: String = " ",
    val marriagePreference: String = " ",
    val gotra: String = " ",
    val permanentAddress: String = " ",
    val permanentPIN: String = " ",
    val permanentResidenceStatus: String = " ",
    val correspondenceAddress: String = " ",
    val correspondencePIN: String = " ",
    val correspondenceResidenceStatus: String = " ",
    val userCorrespondenceLAT: String = " ",
    val userCorrespondenceLONG: String = " ",
    val permanentLat: String = " ",
    val permanentLong: String = " ",
    val highestQualification: String = " ",
    val degree1: String = " ",
    val college1: String = " ",
    val passoutYear1: String = " ",
    val degree2: String = " ",
    val college2: String = " ",
    val passoutYear2: String = " ",
    val occupation: String = " ",
    val annualIncome: String = " ",
    val companyName: String = " ",
    val designation: String = " ",
    val companyAddress: String = " ",
    val totalExperience: String = " ",
    val degreeKey: String = " ",
    val fatherName: String = " ",
    val motherName: String = " ",
    val brothers: String = " ",
    val sisters: String = " ",
    val totalFamily: String = " ",
    val memberLiveTogether: String = " ",
    val areYouLivingWithFamily: String = " ",
    val isJointFamily: String = " ",
    val fatherOccupation: String = " ",
    val motherOccupation: String = " ",
    val dependentUponYou: String = " "
)

data class RegistrationResponse(
    val message: String
)