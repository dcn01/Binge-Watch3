package pl.jakubneukirch.binge_watch.main.mvp

import io.reactivex.Observable
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.api.objects.Airing
import pl.jakubneukirch.binge_watch.api.objects.Serie


open class MainModel(val apiInterface: MovieDBInterface) {

    open fun getAiring():Observable<Airing> = apiInterface.getAiring()

    open fun getSerie(id: Int): Observable<Serie> = apiInterface.getSerie(id)
}