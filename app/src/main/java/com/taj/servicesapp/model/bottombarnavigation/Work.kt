package com.taj.servicesapp.model.bottombarnavigation

import com.google.gson.annotations.SerializedName

data class Work (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("id"   ) var id   : Int?    = null

)