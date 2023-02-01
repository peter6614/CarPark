package com.peter.carpark.viewmodel

import androidx.lifecycle.ViewModel
import com.peter.carpark.bean.CarParkInfo
import com.peter.carpark.repository.CarParkInfoRepository
import com.peter.carpark.util.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CarParkInfoViewModel @Inject constructor(private val repository: CarParkInfoRepository) : ViewModel(){
    private var disposable= mutableListOf<Disposable>()
    val mCarParkInfoListLiveData = SingleLiveData<ArrayList<CarParkInfo>>()
    val errorMessage = SingleLiveData<String>()
    fun getCarParkData() {
        disposable.add( Observable.zip(repository.getAllCarParkDetail(),repository.getAvailableCarParkDetail()
        ) { mCarParkDetailResponse, mCarParkAvailableResponse ->
            repository.mappingCarParkData(mCarParkDetailResponse, mCarParkAvailableResponse)
        }.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { result ->
                    mCarParkInfoListLiveData.value=result
                },
                { e -> errorMessage.postValue(e.message) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}