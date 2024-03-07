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

    fun getMasterLocationData(locationRequest: MasterLocation) {
        val call = service.getMasterLocationData(locationRequest)
        call.enqueue(object : Callback< List<MasterLocationSingleResponse>> {
            override fun onResponse(
                call: Call< List<MasterLocationSingleResponse>>,
                response: Response< List<MasterLocationSingleResponse>>
            ) {
                if (response.isSuccessful) {
                    val userLocation = response.body()
                    Log.d("user_location", userLocation.toString())
                } else {
                    Log.e("user_location", "Failed in data fetching")
                }
            }

            override fun onFailure(call: Call< List<MasterLocationSingleResponse>>, t: Throwable) {
                Log.e("user_location", "error  :${t.message}, \n ${call.isExecuted}")
            }

        })
    }

    fun getMasterInterestData(interest: MasterInterest) {
        val call = service.getMasterInterestData(interest)
        call.enqueue(object : Callback<List<MasterInterestSingleResponse>> {

            override fun onResponse(
                call: Call<List<MasterInterestSingleResponse>>,
                response: Response<List<MasterInterestSingleResponse>>
            ) {
                if (response.isSuccessful) {
                    val userLocation = response.body()
                    Log.d("user_interest", userLocation.toString())
                } else {
                    Log.e("user_interest", "Failed in data fetching")
                }
            }

            override fun onFailure(call: Call<List<MasterInterestSingleResponse>>, t: Throwable) {
                Log.e("user_interest", "error  :${t.message}, \n ${call.isExecuted}")
            }

        })
    }
}