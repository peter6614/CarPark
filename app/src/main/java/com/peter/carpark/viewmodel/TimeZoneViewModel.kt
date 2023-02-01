package com.peter.carpark.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.carpark.bean.JsonBody
import com.peter.carpark.repository.TimeZoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class TimeZoneViewModel @Inject constructor(private val repository: TimeZoneRepository) : ViewModel(){
    private var disposable= mutableListOf<Disposable>()
    val mUpdateTimeZoneLiveData = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    fun updateTimeZone(token:String,objectId:String ,timeZone:String) {
        val timeObj= JSONObject()
        timeObj.put("timezone",timeZone)
        disposable.add( repository.updateTimeZone(token,objectId,JsonBody.create(timeObj.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.e("peter", "result${result}")
                    mUpdateTimeZoneLiveData.value=result.UPDATETIME
                },
                { e -> errorMessage.value=e.message }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}