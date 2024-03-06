package com.petukji.marrageportal

import com.petukji.marrageportal.bottom_nav.data.UserProfile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/getUserPreference")
    fun getUserPreference(): Call<userPreference>

    @POST("/getUserData")
    fun getUserData():Call<UserProfile>

}