package com.petukji.matrimonialapp.bottom_nav.domain.api

import com.petukji.matrimonialapp.bottom_nav.data.api_data.UserProfile
import com.petukji.matrimonialapp.bottom_nav.data.api_data.AllUsersPreference
import com.petukji.matrimonialapp.bottom_nav.data.api_data.UserProfileRequest
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterDegree
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterDegreeResponse
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterInterest
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterInterestSingleResponse
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterLocation
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterLocationSingleResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //user
    @POST("/getUserPreference")
    suspend fun getUserPreference(): Response<AllUsersPreference>

    @POST("/getUserData")
    suspend fun getUserData():Response<Map<String, UserProfile>>
    @POST("/getUserData")
    suspend fun getSingleUserData(@Body request:UserProfileRequest):Response <UserProfile>

    //master
    @POST("/getMaster")
    fun getMasterLocationData(@Body request: MasterLocation):Call< List<MasterLocationSingleResponse>>

    @POST("/getMaster")
    fun getMasterInterestData(@Body request:MasterInterest):Call<List<MasterInterestSingleResponse>>

    @POST("/getMaster")
    fun getMasterDegreeData(@Body request:MasterDegree):Call<MasterDegreeResponse>

}
