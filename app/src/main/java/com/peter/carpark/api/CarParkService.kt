package com.peter.carpark.api

import com.peter.carpark.bean.CarParkAvailableResponse
import com.peter.carpark.bean.CarParkDetailResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface CarParkService {
    @Headers("Content-Type: application/json")
    @GET("TCMSV_alldesc.json")
    fun getAllCarParkDetail(): Observable<CarParkDetailResponse>

    //https://tcgbusfs.blob.core.windows.net/blobtcmsv/TCMSV_allavailable.json
    @Headers("Content-Type: application/json")
    @GET("TCMSV_allavailable.json")
    fun getAvailableCarParkDetail(): Observable<CarParkAvailableResponse>
}