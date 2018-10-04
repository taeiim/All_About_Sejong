package com.god.parktaeim.all_about_sejong.api

import com.god.parktaeim.all_about_sejong.api.model.BicycleStation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenApi {
    @GET("PublicBikeData/getPublicBikeStationList")
    fun getBikeStationList(@Query("action") action: String)
        :Observable<BicycleStation>

}