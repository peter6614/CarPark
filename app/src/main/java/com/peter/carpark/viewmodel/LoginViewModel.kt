package com.peter.carpark.viewmodel

import androidx.lifecycle.ViewModel
import com.peter.carpark.bean.JsonBody
import com.peter.carpark.bean.UserInfo
import com.peter.carpark.repository.LoginRepository
import com.peter.carpark.util.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class LoginViewModel@Inject constructor(private val repository: LoginRepository) : ViewModel(){
    private var disposable= mutableListOf<Disposable>()
    val mLoginLiveData = SingleLiveData<UserInfo>()
    val errorMessage = SingleLiveData<String>()
    fun login(id:String,password:String) {
        val loginInfo= JSONObject()
        loginInfo.put("username",id)
        loginInfo.put("password",password)
        disposable.add( repository.login(JsonBody.create(loginInfo.toString())).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    mLoginLiveData.postValue(result)

                },
                { e -> errorMessage.postValue(e.message) }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}