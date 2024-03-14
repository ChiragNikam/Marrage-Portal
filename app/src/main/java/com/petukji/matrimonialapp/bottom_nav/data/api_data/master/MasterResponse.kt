package com.petukji.matrimonialapp.bottom_nav.data.api_data.master

data class MasterInterestResponse(
    val masterInterestList: List<MasterInterestSingleResponse>
)
data class MasterInterestSingleResponse(
    val interest:String,
    val interestType:String
)

data class MasterLocationResponse(
    val masterLocationList: List<MasterLocationSingleResponse>
)
data class MasterLocationSingleResponse(
    val city:String,
    val state:String,
    val country:String
)

data class MasterDegreeResponse(
    val masterDegreeList: List<MasterDegreeSingleResponse>
)

data class MasterDegreeSingleResponse(
    val degreeName:String
)