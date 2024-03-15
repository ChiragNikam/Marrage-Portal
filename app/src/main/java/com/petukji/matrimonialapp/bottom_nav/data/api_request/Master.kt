package com.petukji.matrimonialapp.bottom_nav.data.api_request

import com.petukji.matrimonialapp.network.ApiService
import com.petukji.matrimonialapp.network.RetrofitInstance

class Master {

    private var service: ApiService =
        RetrofitInstance.getClient("https://us-central1-my-matrimonial-c8514.cloudfunctions.net/")
            .create(ApiService::class.java)
}