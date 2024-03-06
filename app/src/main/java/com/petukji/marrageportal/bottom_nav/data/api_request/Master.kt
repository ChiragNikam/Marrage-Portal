package com.petukji.marrageportal.bottom_nav.data.api_request

import android.util.Log
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocation
import com.petukji.marrageportal.bottom_nav.data.api_data.master.MasterLocationResponse
import com.petukji.marrageportal.bottom_nav.domain.api.ApiService
import com.petukji.marrageportal.bottom_nav.domain.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class Master {

    private var service:ApiService=RetrofitInstance.getClient("https://us-central1-my-matrimonial-c8514.cloudfunctions.net/")
        .create(ApiService::class.java)

    fun getMasterLocationData(locationRequest: MasterLocation){
        val call =service.getMasterLocationData(locationRequest)
        call.enqueue(object:Callback<MasterLocationResponse>{
            override fun onResponse(
                call: Call<MasterLocationResponse>,
                response: Response<MasterLocationResponse>
            ) {
               if (response.isSuccessful){
                   val userLocation=response.body()
                   Log.d("UserLocation",userLocation.toString())
               }
                else{
                    Log.e("userLocation","Failed in data fetching")
               }
            }

            override fun onFailure(call: Call<MasterLocationResponse>, t: Throwable) {
                Log.e("UserLocation", "error  :${t.message}")
            }

        })
    }
}