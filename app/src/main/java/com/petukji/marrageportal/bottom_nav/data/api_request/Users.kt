package com.petukji.marrageportal.bottom_nav.data.api_request

import android.util.Log
import com.petukji.marrageportal.bottom_nav.data.api_data.UserProfile
import com.petukji.marrageportal.bottom_nav.data.api_data.AllUsersPreference
import com.petukji.marrageportal.bottom_nav.domain.api.ApiService
import com.petukji.marrageportal.bottom_nav.domain.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Users {
    private var service: ApiService = RetrofitInstance.getClient("https://us-central1-my-matrimonial-c8514.cloudfunctions.net/")
        .create(ApiService::class.java)

    fun getUserPreference(){
        val call=service.getUserPreference()
        call.enqueue(object :Callback<AllUsersPreference>{
            override fun onResponse(
                call: Call<AllUsersPreference>,
                response: Response<AllUsersPreference>
            ) {
               if (response.isSuccessful){
                   val userPreference=response.body()
                   Log.d("UserPreference",userPreference.toString())
               }else{
                   Log.e("UserPreference","Failed to fetch userPreference")
               }
            }

            override fun onFailure(call: Call<AllUsersPreference>, t: Throwable) {
                Log.e("UserPreference", "Network error :${t.message}")
            }

        })
    }

    fun getUserData(){
        val call = service.getUserData()
        call.enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    Log.d("UserProfile", userProfile.toString())
                } else {
                    Log.e("UserProfile", "Failed to fetch user Profile")
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                Log.e("UserProfile1", "Network error :${t.message}")
            }

        })
    }

}