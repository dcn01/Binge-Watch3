package pl.jakubneukirch.binge_watch.api

import io.reactivex.Observable
import pl.jakubneukirch.binge_watch.api.objects.Airing
import retrofit2.http.GET


interface MovieDBInterface {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/tv/"
        const val AIRING_TODAY = "airing_today"
        const val API = "?api_key="
        const val API_KEY = "948b102611ac966a27ec434331c4cc55"
    }

    @GET("$AIRING_TODAY$API$API_KEY")
    fun getAiring(): Observable<Airing>
}