package com.peter.carpark.repository

import com.peter.carpark.api.CarParkService
import com.peter.carpark.api.LoginService
import com.peter.carpark.bean.CarParkDetailResponse
import com.peter.carpark.bean.JsonBody
import com.peter.carpark.bean.TimeZoneResponse
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import javax.inject.Inject

class TimeZoneRepository @Inject constructor(
    private val mLoginService: LoginService
) {
    fun updateTimeZone(token:String,objectId:String ,body: JsonBody): Observable<TimeZoneResponse> {
        return mLoginService
            .upDateTimeZone(token,objectId,body)
//            .doOnError(this::throwableHttpException)
    }

}