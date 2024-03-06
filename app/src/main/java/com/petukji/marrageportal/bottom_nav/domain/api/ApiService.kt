package com.petukji.marrageportal.bottom_nav.domain.api

import com.petukji.marrageportal.bottom_nav.data.api_data.AllUsersPreference
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {
    @POST("/getUserPreference")
    fun getUserPreference(): Call<AllUsersPreference>
}