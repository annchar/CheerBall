package com.azaless.cheerball.data

import com.azaless.cheerball.view.model.LeagueGroup
import com.azaless.cheerball.view.model.Players
import com.azaless.cheerball.view.model.Team
import com.azaless.cheerball.view.model.Teams
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CheerBallApi {

	@GET("teams/{id}")
	fun getTeam(@Path("id") id: Int): Observable<Team>

	@GET("teams/{id}/players")
	fun getPlayers(@Path("id") id: Int): Observable<Players>

	@GET("competitions/{id}/teams")
	fun getTeams(@Path("id") id: Int): Observable<Teams>

	@GET("competitions/{id}/leagueTable")
	fun getLeagueGroup(@Path("id") id: Int): Observable<LeagueGroup>

	companion object Factory {
		fun create(): CheerBallApi {
			val client = OkHttpClient.Builder()
				.addInterceptor({ chain ->
					val original = chain.request();
					val request = original.newBuilder()
						.header("X-Auth-Token", "2bac367e51c348eca28b784aa751fc17")
						.method(original.method(), original.body())
						.build()

					chain.proceed(request)
				})
				.build()

			val retrofit = retrofit2.Retrofit.Builder()
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.baseUrl(ApiUrl.BASE_URL)
				.build()

			return retrofit.create(CheerBallApi::class.java)
		}
	}
}