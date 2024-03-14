package com.petukji.matrimonialapp.bottom_nav.data.api_data.master

data class MasterLocation(
    val masterType:String="location",
    val queryType:String="read"
)

data class MasterDegree(
    val masterType: String="degree",
    val queryType:String

)

data class MasterInterest(
    val masterType: String="interest",
    val queryType:String
)