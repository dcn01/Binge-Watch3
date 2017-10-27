package pl.jakubneukirch.binge_watch.main.mvp

import io.reactivex.Observable
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.api.objects.Airing


class MainModel(val apiInterface: MovieDBInterface) {

    fun getAiring():Observable<Airing> = apiInterface.getAiring()
}