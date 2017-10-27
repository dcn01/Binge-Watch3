package pl.jakubneukirch.binge_watch.main.mvp

import io.reactivex.Observable
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.api.objects.Airing
import pl.jakubneukirch.binge_watch.api.objects.Serie


class MainModel(val apiInterface: MovieDBInterface) {

    fun getAiring():Observable<Airing> = apiInterface.getAiring()

    fun getSerie(id: Int): Observable<Serie> = apiInterface.getSerie(id)

    fun getSimilar()
}