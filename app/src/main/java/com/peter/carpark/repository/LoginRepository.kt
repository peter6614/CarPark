package com.peter.carpark.repository

import com.peter.carpark.api.LoginService
import com.peter.carpark.bean.JsonBody
import com.peter.carpark.bean.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import org.json.JSONObject
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val mLoginService: LoginService
) {
    fun login(body:JsonBody): Observable<UserInfo> {
        return mLoginService
            .login(body)
//            .doOnError(this::throwableHttpException)
    }
}