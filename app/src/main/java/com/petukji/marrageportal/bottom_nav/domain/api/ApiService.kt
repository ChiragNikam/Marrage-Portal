package com.petukji.marrageportal.bottom_nav.domain.api

import com.petukji.marrageportal.bottom_nav.data.api_data.UserProfile
import com.petukji.marrageportal.bottom_nav.data.api_data.AllUsersPreference
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterDegree
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterDegreeResponse
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterInterest
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterInterestResponse
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterInterestSingleResponse
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocation
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocationResponse
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocationSingleResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //user
    @POST("/getUserPreference")
    fun getUserPreference(): Call<AllUsersPreference>

    @POST("/getUserData")
    fun getUserData():Call<UserProfile>

    //master
    @POST("/getMaster")
    fun getMasterLocationData(@Body request: MasterLocation):Call< List<MasterLocationSingleResponse>>

    @POST("/getMaster")
    fun getMasterInterestData(@Body request:MasterInterest):Call<List<MasterInterestSingleResponse>>

    @POST("/getMaster")
    fun getMasterDegreeData(@Body request:MasterDegree):Call<MasterDegreeResponse>

}
