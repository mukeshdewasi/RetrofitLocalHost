package com.example.androidretrofitlocalhost.Retrofit

import android.telecom.Call
import com.example.androidretrofitlocalhost.Model.Student
import com.example.androidretrofitlocalhost.Model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("student.php")
    fun createAccount(
        @Field("flag")flag:Int,
        @Field("name")name: String,
        @Field("email")email:String,
        @Field("mobile")mobile:String
    ):retrofit2.Call<User>
}