package com.taj.servicesapp.apiservice

import com.taj.servicesapp.apiservice.ApiService.EndPoint.ALL_WORK
import com.taj.servicesapp.apiservice.ApiService.EndPoint.COMPLETED_ORDERS
import com.taj.servicesapp.apiservice.ApiService.EndPoint.CREATE_ORDER
import com.taj.servicesapp.apiservice.ApiService.EndPoint.LOGIN_END_POINT
import com.taj.servicesapp.apiservice.ApiService.EndPoint.PENDING_ORDERS
import com.taj.servicesapp.apiservice.ApiService.EndPoint.REGISTER_END_POINT
import com.taj.servicesapp.apiservice.ApiService.EndPoint.UN_COMPLETED_ORDERS
import com.taj.servicesapp.model.bottombarnavigation.AllServices
import com.taj.servicesapp.model.auth.LoginRequest
import com.taj.servicesapp.model.auth.LoginResponse
import com.taj.servicesapp.model.auth.RegisterRequest
import com.taj.servicesapp.model.auth.RegisterResponse
import com.taj.servicesapp.model.bottombarnavigation.AllOrders
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @GET(ALL_WORK)
    suspend fun getAllServices(): AllServices

    @POST(LOGIN_END_POINT)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(REGISTER_END_POINT)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @FormUrlEncoded
    @POST(CREATE_ORDER)
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Field("user_id") userId: Int,
        @Field("work_id") workId: Int,
        @Field("details") details: String,
        @Field("details_address") detailsAddress: String,
        @Field("lat") lat: String,
        @Field("long") long: String,
        @Field("phone") phone: String,
    ): Response<AllOrders>

    @GET(COMPLETED_ORDERS)
    suspend fun getCompletedOrders(@Header("Authorization") token: String): AllOrders


    @GET(PENDING_ORDERS)
    suspend fun getPendingOrders(@Header("Authorization") token: String): AllOrders

    @GET(UN_COMPLETED_ORDERS)
    suspend fun getUnCompletedOrders(@Header("Authorization") token: String): AllOrders


    companion object {
        var apiService: ApiService? = null

        fun geInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://studentucas.awamr.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }


    }

    object EndPoint {
        const val ALL_WORK = "all/works"
        const val LOGIN_END_POINT = "auth/login/user"
        const val REGISTER_END_POINT = "auth/register/user"
        const val CREATE_ORDER = "create/order"
        const val COMPLETED_ORDERS = "order/complete/user"
        const val PENDING_ORDERS = "order/pending/user"
        const val UN_COMPLETED_ORDERS = "order/un/complete/user"
    }
}

