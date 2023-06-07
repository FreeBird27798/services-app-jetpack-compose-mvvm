package com.taj.servicesapp.model.auth

import androidx.datastore.core.Serializer
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.InputStream
import java.io.OutputStream


data class UserData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("photo") var photo: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("active") var active: String? = null,
    @SerializedName("token") var token: String? = null
)

