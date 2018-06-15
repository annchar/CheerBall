package com.azaless.cheerball.data

import com.azaless.cheerball.view.model.Team
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CheerBallApi {

    @GET("teams/{id}")
    fun getTeam(@Path("id") id: Int): Observable<Team>


    companion object Factory {
        fun create(): CheerBallApi {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ApiUrl.BASE_URL)
                    .build()

            return retrofit.create(CheerBallApi::class.java)
        }
    }
}