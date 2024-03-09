package com.petukji.marrageportal.bottom_nav.data.api_request

import android.util.Log
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterInterest
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterInterestResponse
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterInterestSingleResponse
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocation
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocationResponse
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocationSingleResponse
import com.petukji.marrageportal.bottom_nav.domain.api.ApiService
import com.petukji.marrageportal.bottom_nav.domain.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class Master {

    private var service: ApiService =
        RetrofitInstance.getClient("https://us-central1-my-matrimonial-c8514.cloudfunctions.net/")
            .create(ApiService::class.java)
}