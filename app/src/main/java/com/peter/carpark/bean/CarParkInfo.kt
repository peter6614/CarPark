package com.peter.carpark.bean

data class CarParkInfo(var id: String,
                       var name: String,
                       var address: String,
                       var totalcar: Int,
                       var availablecar: Int,
                       var ChargeStation: ChargeStation?){
    var standBy=0;
    var charge=0;
    constructor() : this("", "",
        "", 0, 0,
        com.peter.carpark.bean.ChargeStation(null)
    )
    fun calculateChargeStationStatus(){
        ChargeStation?.let {
            it.scoketStatusList?.forEach { mScoketStatus->
                if(mScoketStatus.spot_status=="充電中"){
                    charge++
                }else{
                    standBy++
                }
            }
        }
    }
}
