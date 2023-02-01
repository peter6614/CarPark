package com.peter.carpark.repository

import android.util.Log
import com.peter.carpark.api.CarParkService
import com.peter.carpark.bean.CarParkAvailableResponse
import com.peter.carpark.bean.CarParkDetailResponse
import com.peter.carpark.bean.CarParkInfo
import io.reactivex.Observable
import javax.inject.Inject


class CarParkInfoRepository @Inject constructor(
    private val mCarParkService: CarParkService
) {
    fun getAllCarParkDetail(): Observable<CarParkDetailResponse> {
        return mCarParkService
            .getAllCarParkDetail()
//            .doOnError(this::throwableHttpException)
    }
    fun getAvailableCarParkDetail(): Observable<CarParkAvailableResponse> {
        return mCarParkService
            .getAvailableCarParkDetail()
//            .doOnError(this::throwableHttpException)
    }
    fun mappingCarParkData(mCarParkDetailResponse:CarParkDetailResponse, mCarParkAvailableResponse:CarParkAvailableResponse):ArrayList<CarParkInfo> {
        Log.e("peter","mCarParkDetailResponse:${mCarParkDetailResponse.data.park.size}")
        Log.e("peter","mCarParkAvailableResponse:${mCarParkAvailableResponse.data.park.size}")
        val carParkDetailMap = mCarParkDetailResponse.data.park.associateBy { it.id }
        val carParkInfoList = arrayListOf<CarParkInfo>()
        mCarParkAvailableResponse.data.park.forEach(){  carParkAvailableItem->
            carParkDetailMap[carParkAvailableItem.id]?.let {carParkDetailItem->
                carParkInfoList.add(CarParkInfo().apply {
                    id=carParkDetailItem.id
                    address=carParkDetailItem.address
                    name=carParkDetailItem.name
                    totalcar=carParkDetailItem.totalcar
                    availablecar=carParkAvailableItem.availablecar
                    ChargeStation=carParkAvailableItem.ChargeStation
                    calculateChargeStationStatus()
                })
            }
        }
        return carParkInfoList
    }
}