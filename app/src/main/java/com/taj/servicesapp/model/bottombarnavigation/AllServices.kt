package com.taj.servicesapp.model.bottombarnavigation

import com.google.gson.annotations.SerializedName

data class AllServices (

    @SerializedName("code"    ) var code    : Int?            = null,
    @SerializedName("success" ) var success : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<ServiceData> = arrayListOf()

)