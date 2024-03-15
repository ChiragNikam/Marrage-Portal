package com.petukji.matrimonialapp.api

import com.petukji.matrimonialapp.auth.data.api_data.RegistrationRequestData
import com.petukji.matrimonialapp.auth.data.api_data.RegistrationResponse
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfile
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.AllUsersPreference
import com.petukji.matrimonialapp.bottom_nav.data.api_data.user.UserProfileRequest
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterDegree
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterDegreeResponse
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterInterest
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterInterestSingleResponse
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterLocation
import com.petukji.matrimonialapp.bottom_nav.data.api_data.master.MasterLocationSingleResponse
import com.petukji.matrimonialapp.member_info.data.api_data.LogDataResponse
import com.petukji.matrimonialapp.member_info.data.api_data.ShortListLogDataResponse
import com.petukji.matrimonialapp.member_info.data.api_data.ShortlistWriteRequest
import com.petukji.matrimonialapp.member_info.data.api_data.ViewLogWriteRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    // register user
    @POST("/registerUser")
    suspend fun registerUser(@Body request: RegistrationRequestData): Response<RegistrationResponse>

    //user
    @POST("/getUserPreference")
    suspend fun getUserPreference(): Response<AllUsersPreference>

    @POST("/getUserData")
    suspend fun getUserData():Response<Map<String, UserProfile>>
    @POST("/getUserData")
    suspend fun getSingleUserData(@Body request: UserProfileRequest):Response <UserProfile>

    //master
    @POST("/getMaster")
    fun getMasterLocationData(@Body request: MasterLocation):Call< List<MasterLocationSingleResponse>>

    @POST("/getMaster")
    fun getMasterInterestData(@Body request:MasterInterest):Call<List<MasterInterestSingleResponse>>

    @POST("/getMaster")
    fun getMasterDegreeData(@Body request:MasterDegree):Call<MasterDegreeResponse>

    @POST("/connectViewLog")
    fun sendConnectViewLog(@Body request: ViewLogWriteRequest):Call <LogDataResponse>


    @POST("/connectShortlistedLog")
    fun shortListLog(@Body request:ShortlistWriteRequest):Call<ShortListLogDataResponse>

}
