package com.peter.carpark.bean

import com.google.gson.annotations.SerializedName

data class CarParkAvailableResponse(
    @SerializedName("data")
    val data: AvailableData
)

data class AvailableData(

    @SerializedName("UPDATETIME")
    val UPDATETIME: String,
    @SerializedName("park")
    val park: List<AvailablePark>
)


data class AvailablePark(
    val ChargeStation: ChargeStation,
    val availablebus: Int,
    val availablecar: Int,
    val availablemotor: Int,
    val id: String
)

data class ChargeStation(
    val scoketStatusList: List<ScoketStatus>?
)

data class ScoketStatus(
    val spot_abrv: String,
    val spot_status: String
)