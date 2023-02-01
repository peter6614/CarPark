package com.peter.carpark.api

import com.peter.carpark.bean.JsonBody
import com.peter.carpark.bean.TimeZoneResponse
import com.peter.carpark.bean.UserInfo
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface LoginService {
    //https://noodoe-app-development.web.app/api/login
    @Headers("Content-Type: application/json","X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD","X-Parse-Revocable-Session: 1")
    @POST("api/login")
    fun login(@Body body: JsonBody): Observable<UserInfo>

    //Url:https://noodoe-app-development.web.app/api/users/{user object id}
    @Headers("Content-Type: application/json","X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD","X-Parse-REST-API-Key: 321")
    @PUT("api/users/{obid}")
    fun upDateTimeZone(@Header("X-Parse-Session-Token") token:String,@Path("obid") key:String ,@Body body: JsonBody): Observable<TimeZoneResponse>

}