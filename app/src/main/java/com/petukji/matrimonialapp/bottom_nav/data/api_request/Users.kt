package com.petukji.matrimonialapp.bottom_nav.data.api_request

import com.petukji.matrimonialapp.bottom_nav.domain.api.ApiService
import com.petukji.matrimonialapp.bottom_nav.domain.api.RetrofitInstance

class Users {
    var service: ApiService =
        RetrofitInstance.getClient("https://us-central1-my-matrimonial-c8514.cloudfunctions.net/")
            .create(ApiService::class.java)
}