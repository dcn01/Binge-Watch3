package pl.jakubneukirch.binge_watch.api

import io.reactivex.Observable
import pl.jakubneukirch.binge_watch.api.objects.Airing
import pl.jakubneukirch.binge_watch.api.objects.Serie
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieDBInterface {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/tv/"
        const val AIRING_TODAY = "airing_today"
        const val API = "?api_key="
        const val API_KEY = "948b102611ac966a27ec434331c4cc55"
        const val API_URL_IMAGE_W780 = "https://image.tmdb.org/t/p/w780/"
        const val API_URL_IMAGE_W500 = "https://image.tmdb.org/t/p/w500/"
        const val API_URL_IMAGE_W342 = "https://image.tmdb.org/t/p/w342/"
        const val API_URL_IMAGE_W185 = "https://image.tmdb.org/t/p/w185/"
        const val API_URL_IMAGE_W92 = "https://image.tmdb.org/t/p/w92/"
    }

    @GET("$AIRING_TODAY$API$API_KEY")
    fun getAiring(): Observable<Airing>

    @GET("{id}$API$API_KEY")
    fun getSerie(@Path("id") id: Int): Observable<Serie>
}