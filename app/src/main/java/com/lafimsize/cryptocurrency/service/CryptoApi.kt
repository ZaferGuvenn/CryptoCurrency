package com.lafimsize.cryptocurrency.service

import com.lafimsize.cryptocurrency.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoApi {

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Call<List<CryptoModel>>
}