package com.petukji.marrageportal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/getUserPreference")
    fun getUserPreference(): Call<userPreference>
}