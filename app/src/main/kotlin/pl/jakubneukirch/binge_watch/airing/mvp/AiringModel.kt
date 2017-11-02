package pl.jakubneukirch.binge_watch.airing.mvp

import io.reactivex.Observable
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.api.objects.Airing

open class AiringModel(val apiInterface: MovieDBInterface) {

    open fun getAiring(): Observable<Airing> = apiInterface.getAiring()
}